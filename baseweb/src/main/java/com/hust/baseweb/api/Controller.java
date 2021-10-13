package com.hust.baseweb.api;

import com.hust.baseweb.entity.UserLogin;
import com.hust.baseweb.model.AddModel;
import com.hust.baseweb.repo.UserLoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin

public class Controller {
    @Autowired
    private UserLoginRepo userLoginRepo;

    @PostMapping("/")
    public ResponseEntity<?> home(Principal principal){
        System.out.println("Home");
        Map<String, String> res = new HashMap();
        res.put("users",principal.getName());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Expose-Headers","X-Auth-Token");
        return ResponseEntity.ok().headers(responseHeaders).body(res);
    }

    @GetMapping("/public/greetings")
    public ResponseEntity<?> greetings(Principal principal){
        return ResponseEntity.ok().body("Greetings from server....");
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(Principal principal, @RequestBody AddModel input){
        int c = input.getA() + input.getB();
        return ResponseEntity.ok().body("C = " + c);
    }

    @GetMapping("/list-users")
    public ResponseEntity<?> getLisUsers(Principal principal){
        System.out.println("getListUsers, user = " + principal.getName());
        List<UserLogin> userLoginList = userLoginRepo.findAll();
        return ResponseEntity.ok().body(userLoginList);
    }
    @GetMapping("/public/list-page-users")
    public ResponseEntity<?> listPageUsers(Principal principal,
    @RequestParam int page, @RequestParam int size, Pageable pageable){
        System.out.println("listPageUsers, page = " + page + " size = " + size + ", pageable.page = " + pageable.getPageNumber()
        + " pageable.size = " + pageable.getPageSize() + ", offset = " + pageable.getOffset());

        //int pageNum = 0; int size = 5;
        Pageable aPage = PageRequest.of(page,size);
        System.out.println("listPageUsers, pageNum = " + page + " size = " + size + " pageOffset = " + aPage.getOffset() + ", pageSize = "
                + aPage.getPageSize() + ", pageNumber = " + aPage.getPageNumber());
        //Page<UserLogin> userLoginPage = userLoginRepo.findAll(aPage);

        // second way is to use query
        List<UserLogin> lst = userLoginRepo.findAllQuery(page*size,size);
        int totalCount = userLoginRepo.countAllUsers();
        System.out.println("totalCount = " + totalCount);
        Page<UserLogin> userLoginPage = new PageImpl<>(
                lst,pageable,totalCount
        );

        return ResponseEntity.ok().body(userLoginPage);
    }
}

