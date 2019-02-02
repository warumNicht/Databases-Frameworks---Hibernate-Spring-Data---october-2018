package com.example.demo;

import com.example.demo.pb2UserSystem.models.*;
import com.example.demo.pb2UserSystem.services.*;
import com.example.demo.pb2UserSystem.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {
    private final FileUtil fileUtil;
    private CountryService countryService;
    private TownService townService;
    private PictureService pictureService;
    private AlbumService albumService;
    private UserService userService;

    @Autowired
    public ConsoleRunner(CountryService countryService, TownService townService,
                         PictureService pictureService, AlbumService albumService,
                         UserService userService, FileUtil fileUtil) {
        this.countryService = countryService;
        this.townService = townService;
        this.pictureService = pictureService;
        this.albumService = albumService;
        this.userService = userService;
        this.fileUtil = fileUtil;
    }

    @Override
    public void run(String... strings) throws Exception {

        this.seedData();
//
//        this.getUsersByEmailProvider("ministerium-aussen.de"); //pb 1
//
//        this.removeInactiveUsers(); //pb 2


        /**
         * Test custom annotations:
         * The code below Should throw following exception messages:
         *
         * Invalid email!
         * Username must have between 4 and 30 symbols!
         * Age must be between 1 and 120!
         * The password must have at least 5 symbols!
         */
//        Picture picture = this.pictureService.getPictureRepository().findOne(4);
//        Town livingTown = this.townService.getTownRepository().findOne(20);
//        Town bornTown = this.townService.getTownRepository().findOne(15);
//
//        User user = new User("Ces", "W", "cesar@ro@ma.fr", picture,
//                new Date(), new Date(), -55, "Alain", "Delon", bornTown, livingTown);
//        try {
//            this.userService.getUserRepository().save(user);
//        } catch (ConstraintViolationException e) {
//            e.getConstraintViolations().stream()
//                    .forEach(c -> System.out.println(c.getMessage()));
//        }

    }

    private void seedData() throws IOException, ParseException {
        this.seedCountries();
        this.seedTowns();
        this.seedPictures();
        this.seedUsers();
        this.seedAlbums();
    }

    /**
     * pb1. Get Users by Email Provider
     *
     * @param domain
     */

    private void getUsersByEmailProvider(String domain) {
        List<User> allByEmailEndingWith = this.userService.getUserRepository().getAllByEmailEndingWith("@" + domain);
        if (allByEmailEndingWith.isEmpty()) {
            System.out.printf("No users found with email domain %s%n", domain);
        } else {
            for (User user : allByEmailEndingWith) {
                System.out.printf("%-20s %s%n", user.getUserName(), user.getEmail());
            }
        }
    }

    /**
     * pb 2. Remove Inactive Users
     */
    private void removeInactiveUsers() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter date in format:  d MMM yyyy");
        String dateInput = scanner.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("d MMM yyyy");
        Date date = format.parse(dateInput);
        this.deleteUsersBeingNotLoggedAfter(date); //set isDeleted to true where condition
    }

    private void deleteUsersBeingNotLoggedAfter(Date date) {
        List<User> users = this.userService
                .getUserRepository().getAllByDeletedIsFalseAndLastTimeLoggedInBefore(date);
        if (users.isEmpty()) {
            System.out.println("No users have been deleted");
        } else {
            for (User user : users) {
                user.setDeleted(true);
                this.userService.getUserRepository().save(user);
            }
            System.out.printf("%d user%s ha%s been deleted%n", users.size(),
                    users.size() > 1 ? "s" : "", users.size() > 1 ? "s" : "ve");
        }
    }

    private void setUsersFriends() {
        int usersCount = (int) this.userService.getUserRepository().count();
        Random random = new Random();
        for (int i = 1; i <= usersCount; i++) {
            User user = this.userService.getUserRepository().findOne(i);
            int countOfFriends = random.nextInt(usersCount);
            Set<User> friends = new HashSet<>();
            for (int j = 0; j < countOfFriends; j++) {
                User currentFriend = this.userService.getUserRepository().findOne(random.nextInt(usersCount) + 1);
                if (!user.equals(currentFriend)) {
                    friends.add(currentFriend);
                }
            }
            user.setFriends(friends);
            this.userService.getUserRepository().save(user);
        }
    }

    private void seedUsers() throws IOException, ParseException {
        if (this.userService.getUserRepository().count() != 0) {
            return;
        }
        List<String> fileContent = this.fileUtil
                .getFileContent("src\\main\\resources\\userSystemData\\users.txt");
        SimpleDateFormat formatter = new SimpleDateFormat("d-MM-yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("d-MM-yyyy HH:mm:ss");
        Random random = new Random();
        for (String userTockens : fileContent) {
            String[] tokens = userTockens.split("\\s+");
            String username = tokens[0];
            String password = tokens[1];
            String email = tokens[2];
            String registeredOn = tokens[3];
            Date registrationDate = formatter.parse(registeredOn);
            String lastLogged = tokens[4] + " " + tokens[5];
            Date lastLoggedDate = formatter2.parse(lastLogged);
            int age = Integer.parseInt(tokens[6]);
            String firstName = tokens[7];
            String lastName = tokens[8];

            int townCount = (int) this.townService.getTownRepository().count();

            Town born = this.townService.getTownRepository()
                    .findOne(random.nextInt(townCount) + 1);
            Town living = this.townService.getTownRepository()
                    .findOne(random.nextInt(townCount) + 1);
            User user = new User();
            user.setBornTown(born);
            user.setLivingTown(living);
            user.setUserName(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setRegisteredOn(registrationDate);
            user.setLastTimeLoggedIn(lastLoggedDate);
            user.setAge(age);
            user.setFirstName(firstName);
            user.setLastName(lastName);

            int picturesCount = (int) this.pictureService.getPictureRepository().count();
            Picture picture = this.pictureService
                    .getPictureRepository().findOne(random.nextInt(picturesCount) + 1);
            user.setProfilePicture(picture);

            this.userService.getUserRepository().save(user);
        }
        this.setUsersFriends();
    }

    private void seedCountries() throws IOException {
        if (this.countryService.getCountryRepository().count() == 0) {
            List<String> countriesString = this.fileUtil
                    .getFileContent("src\\main\\resources\\userSystemData\\countries.txt");
            for (String currentCountry : countriesString) {
                Country country = new Country();
                country.setName(currentCountry);
                this.countryService.addCountry(country);
            }
        }
    }

    private void seedTowns() throws IOException {
        if (this.townService.getTownRepository().count() == 0) {
            List<String> fileContent = this.fileUtil
                    .getFileContent("src\\main\\resources\\userSystemData\\towns.txt");
            int count = (int) this.countryService.getCountryRepository().count();
            Random random = new Random();
            for (String townString : fileContent) {
                int currentCountryId = random.nextInt(count) + 1;
                Country country = this.countryService.getCountryRepository().findOne(currentCountryId);

                Town town = new Town();
                town.setName(townString);
                town.setCountry(country);

                this.townService.getTownRepository().save(town);
            }
        }
    }

    private void seedPictures() throws IOException, ParseException {
        if (this.pictureService.getPictureRepository().count() != 0) {
            return;
        }
        File fileImagesDirectory = new File("src\\main\\resources\\userSystemData\\pictures");
        File[] files = fileImagesDirectory.listFiles();

        List<String> pictureContents = this.fileUtil
                .getFileContent("src\\main\\resources\\userSystemData\\pictures.txt");
        for (int i = 0; i < files.length; i++) {
            byte[] fileContent = Files.readAllBytes(files[i].toPath());
            String absolutePath = files[i].getAbsolutePath();
            String fileExtension = absolutePath.substring(absolutePath.lastIndexOf(".") + 1);

            String currentPictureDetails = pictureContents.get(i);

            String captionDateStr = currentPictureDetails.substring(currentPictureDetails.lastIndexOf(" "));
            String title = currentPictureDetails.substring(0, currentPictureDetails.lastIndexOf(" "));

            SimpleDateFormat formatter = new SimpleDateFormat("d-MM-yyyy");
            Date captionDate = formatter.parse(captionDateStr);

            Picture picture = new Picture(title, captionDate, absolutePath, fileExtension, fileContent);

            this.pictureService.getPictureRepository().save(picture);
        }

    }

    private void seedAlbums() throws IOException {
        if (this.albumService.getAlbumRepository().count() == 0) {
            List<String> fileContent = this.fileUtil
                    .getFileContent("src\\main\\resources\\userSystemData\\albums.txt");

            int countOfPictures = (int) this.pictureService.getPictureRepository().count();
            int countOfUsers = (int) this.userService.getUserRepository().count();
            Random random = new Random();

            for (String albumStr : fileContent) {
                String[] tockens = albumStr.trim().split("\\s+");
                StringBuilder name = new StringBuilder();
                for (int i = 0; i < tockens.length - 2; i++) {
                    name.append(tockens[i] + " ");
                }
                String albumName = name.toString().trim();
                String color = tockens[tockens.length - 2];
                String isPublic = tockens[tockens.length - 1];

                int picturesPerAlbum = random.nextInt(countOfPictures);
                Set<Picture> pictures = new HashSet<>();

                for (int i = 0; i < picturesPerAlbum; i++) {
                    int currentPictureId = random.nextInt(countOfPictures) + 1;
                    Picture picture = this.pictureService.getPictureRepository().findOne(currentPictureId);
                    pictures.add(picture);
                }
                User user = this.userService.getUserRepository().findOne(random.nextInt(countOfUsers) + 1);
                Album album = new Album();
                album.setUser(user);
                album.setName(albumName);
                album.setBackgroundColor(color);
                album.setPictures(pictures);
                if (isPublic.equals("1")) {
                    album.setPublic(true);
                }
                this.albumService.getAlbumRepository().save(album);
            }
        }
    }

}
