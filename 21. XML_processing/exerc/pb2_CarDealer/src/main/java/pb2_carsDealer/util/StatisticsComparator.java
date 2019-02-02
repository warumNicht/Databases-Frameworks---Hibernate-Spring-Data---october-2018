package pb2_carsDealer.util;

import pb2_carsDealer.domain.dto.query5.CustomerStatisticsDto;

import java.util.Comparator;

public class StatisticsComparator  implements Comparator<CustomerStatisticsDto> {

    @Override
    public int compare(CustomerStatisticsDto o1, CustomerStatisticsDto o2) {

        int firstCriteria=o2.getSpentMoney().compareTo(o1.getSpentMoney());
        if(firstCriteria==0){
            return Integer.compare(o2.getBoughtCars(),o1.getBoughtCars());
        }
        return firstCriteria;
    }
}