package entityDTO.utils;

import entityDTO.db.Customer;
import entityDTO.db.Ressource;
import entityDTO.dto.CustomerDTO;
import entityDTO.dto.RessourceDTO;

public class DtoConverter {

    public static Customer customerDtoToCustomer(CustomerDTO customerDTO) {
        Customer c = new Customer();
        c.setEmail(customerDTO.getEmail());
        c.setName(customerDTO.getName());
        c.setPhone(customerDTO.getPhone());
        c.setPwd(customerDTO.getPwd());
        c.setSurname(customerDTO.getSurname());
        c.setTrackingId(customerDTO.getTrackingId());
        return c;
    }

    public static CustomerDTO customerToCustomerDto(Customer customer) {
        CustomerDTO c = new CustomerDTO();
        c.setEmail(customer.getEmail());
        c.setName(customer.getName());
        c.setPhone(customer.getPhone());
        c.setPwd(customer.getPwd());
        c.setSurname(customer.getSurname());
        c.setTrackingId(customer.getTrackingId());
        return c;
    }

    public static Ressource ressourceDtoToRessource(RessourceDTO res) {
        // TODO Auto-generated method stub

        Ressource r = new Ressource();
        r.setApplication(res.getApplication());
        r.setClientTid(res.getClientTid());
        r.setIp(res.getIp());
        r.setOs(res.getOs());
        r.setPlace(res.getPlace());
        r.setProvider(res.getProvider());
        r.setPwd(res.getPwd());
        r.setSize(res.getSize());
        r.setStatus(res.getStatus());
        r.setTrackingId(res.getTrackingId());
        return r;
    }

}
