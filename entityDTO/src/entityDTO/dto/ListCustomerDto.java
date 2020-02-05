package entityDTO.dto;

import java.io.Serializable;
import java.util.List;

public class ListCustomerDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1825057681119223354L;
    private List<CustomerDTO> listCustomerDto;

    /**
     * @return the listCustomerDto
     */
    public List<CustomerDTO> getListCustomerDto() {
        return listCustomerDto;
    }

    /**
     * @param listCustomerDto the listCustomerDto to set
     */
    public void setListCustomerDto(List<CustomerDTO> listCustomerDto) {
        this.listCustomerDto = listCustomerDto;
    }

}
