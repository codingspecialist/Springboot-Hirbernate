package shop.mtcoding.hibernatestudy.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String tel;

    public Customer() {
        System.out.println("Jackson 발동시 디폴트 생성자 실행");
    }

    public Customer(Long id, String name, String tel){
        System.out.println("조회시에 mapper 동작할 때 풀 생성자 실행");
        this.id = id;
        this.name = name;
        this.tel = tel;
    }

    public void update(String name, String tel){
        this.name = name;
        this.tel = tel;
    }
}
