# UserManagementValidation

##### :green_square: Its a API Based Web Application
## :one: Frameworks and Languages Used -
    1. SpringBoot
    2. JAVA
    3. Postman
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
## :two: Dataflow (Functions Used In)
### :green_square: 1. Model - Model is used to Iniitialize the required attributes and create the accessable constructors and methods
#### :large_orange_diamond: User.java
```java
package com.example.UserManagementValidation.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String UserId;

    @NotEmpty(message = "Not Suitable UserName")
    @Pattern(regexp = "^[A-Za-z]\\w{5,29}$")
    private String UserName;

    @NotEmpty(message = "Please make sure this DOB is correct")
    @Pattern(regexp = "^(1[0-2]|0[1-9])-(3[01]" + "|[12][0-9]|0[1-9])-[0-9]{4}$")
    private String dob;

    @NotEmpty
    @Email(message = "Email is not correct")
    private String email;

    @NotEmpty(message = "Phone Number is empty")
    @Size(min = 10, max = 12)
    //@Pattern(regexp = "^\\d{12}$") //Normal 12 digit phoneNumber validation regex
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{4,5}[- .]?\\d{5}$") //International PhoneNumber
    //@Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$") //some Country PhoneNumber
    private String phone;

    private String date;
    private String time;
}
```

##### To See Model
:white_check_mark: [UserManagement-Validation-Model](https://github.com/harshsikarwar20/UserManagementValidation/tree/master/src/main/java/com/example/UserManagementValidation/Model)
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

### :green_square: 2. Service - This Layer is used to write the logic of our CRUD operaions in RestroService.java
#### :large_orange_diamond: UserService.java
```java
package com.example.UserManagementValidation.Service;

import com.example.UserManagementValidation.Dao.UserRepository;
import com.example.UserManagementValidation.Model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public User getUserById(String id){
        List<User> newUserList = userRepository.getAllUsers();

        for(User user : newUserList){
            if(user.getUserId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public String saveUser(User user){
        userRepository.save(user);
        return "User Saved Successfully!!!";
    }

    public String updateUser(String id, User newUser){
        List<User> newUserList = userRepository.getAllUsers();

        for(User user : newUserList){
            if(user.getUserId().equals(id)){
                userRepository.save(newUser);
            }
        }
        return "user Updated!!!";
    }

    public String deleteUser(String id){
        boolean deleted = false;
        deleted = userRepository.remove(id);
        if(deleted){
            return "User Deleted!!!";
        }
        return "There is no user exist related to this Id!!!";
    }
}
```

#### To See Service
:white_check_mark: [User-Service](https://github.com/harshsikarwar20/UserManagementValidation/tree/master/src/main/java/com/example/UserManagementValidation/Service)
----------------------------------------------------------------------------------------------------------------------------------------------------

### :green_square: 3. Controller - This Controller is used to like UI between Model and Service and also for CRUD Mappings in RestroController.java
#### :large_orange_diamond: UserController.java
```java
package com.example.UserManagementValidation.Controller;

import com.example.UserManagementValidation.Model.User;
import com.example.UserManagementValidation.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class UserController {

    @Autowired
    UserService userService;

    //Get All User
    @GetMapping(value = "/getAllUsers")
    public List<User> GetAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/getUserById/{id}", method= RequestMethod.GET)
    public User getUserById(String userId){
        return userService.getUserById(userId);
    }

    @PostMapping(value = "/addUser")
    public String saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping(value = "/updateUser/id/{id}")
    public String updateUser(@PathVariable String userId, @RequestBody User user){
        return userService.updateUser(userId, user);
    }

    @DeleteMapping(value = "/deleteUser/id/{id}")
    public String deleteUser(String id){
        return userService.deleteUser(id);
    }


}
```

#### To See Controller
:white_check_mark: [User-Controller](https://github.com/harshsikarwar20/UserManagementValidation/tree/master/src/main/java/com/example/UserManagementValidation/Controller)
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
## :three: DataStructures Used in Project
    1. ArrayList
-------------------------------------------------------------------------------------------------------------------------------------------------------
## :four: Project Summary
### :large_orange_diamond: Project result 
#### :purple_square: POST API : http://localhost:8080/addUser


#### :purple_square: GET API (For All Restaurent) : http://localhost:8080/api/restro-app/findAll-restaurent

#### :purple_square: PUT API (For All Restaurent) : http://localhost:8080/api/restro-app/update-restaurent/number/{number}

#### :purple_square: PUT API (For All Restaurent) : http://localhost:8080/api/restro-app/delete-restaurent/number/1001
----------------------------------------------------------------------------------------------------------------------------------------------------------
