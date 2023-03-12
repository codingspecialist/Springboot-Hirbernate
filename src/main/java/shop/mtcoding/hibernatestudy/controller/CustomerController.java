package shop.mtcoding.hibernatestudy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.hibernatestudy.dto.ResponseDto;
import shop.mtcoding.hibernatestudy.model.Customer;
import shop.mtcoding.hibernatestudy.model.CustomerNativeQueryRepository;

import java.util.List;

/**
 * 1. 요청 DTO는 나중에 배움
 * 2. Service도 나중에 배움 (트랜잭션 처리 commit, rollback)
 * 3. 이번장 목표 : Hibernate
 * 4. 숙제 Controller 테스트 코드, Repository 테스트 코드 작성
 */
@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerNativeQueryRepository customerRepository;
    //private final CustomerRepository customerRepository;

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Customer customerPS = customerRepository.findById(id);
        ResponseDto<?> dto = new ResponseDto<>("한건 조회 성공", customerPS);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int page){
        List<Customer> customerListPS = customerRepository.findAll(page);
        ResponseDto<?> dto = new ResponseDto<>("전체 조회 성공", customerListPS);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Customer customer){
        // 1. 존재 여부 확인 : 최소한의 트랜잭션 발동 and Setter 만들지 않기
        Customer customerPS = customerRepository.findById(id);
        
        // 2. 객체 값 변경
        customerPS.update(customer.getName(), customer.getTel());
        
        // 3. 변경된 객체로 DB 수정
        customerRepository.update(customerPS);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        // 1. 존재 여부 확인 : 최소한의 DB 트래픽
        Customer customerPS = customerRepository.findById(id);
        
        // 2. 존재하면 삭제
        customerRepository.delete(customerPS);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
