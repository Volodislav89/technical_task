package com.zvl.technical_task.controller;

import com.zvl.technical_task.exception.UserException;
import com.zvl.technical_task.model.User;
import com.zvl.technical_task.model.UserVoicePrintDTO;
import com.zvl.technical_task.model.VoicePrint;
import com.zvl.technical_task.model.VoicePrintDTO;
import com.zvl.technical_task.service.UserService;
import com.zvl.technical_task.service.VoicePrintService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    private UserService userService;
    private VoicePrintService voicePrintService;

    @PostMapping
    public User saveUserAndVoicePrint(@RequestBody UserVoicePrintDTO userVoicePrintDTO) {
        log.info("Invocation of saveUserAndVoicePrint() method.");
        User user = userService.saveNewUser(userVoicePrintDTO.getUser());
        log.info("Invocation of saveNewUser() method from UserService.");
        VoicePrint voicePrint = userVoicePrintDTO.getVoicePrint();
        voicePrint.setUserHashCode(user.hashCode());
        log.info("setting userHasCode to voicePrint");
        voicePrintService.saveNewVoicePrint(voicePrint);
        log.info("Invocation of saveNewVoicePrint() method from VoicePrintService.");
        return user;
    }

    @PostMapping("/list")
    public List<UserVoicePrintDTO> saveUserAndVoicePrint(@RequestBody List<UserVoicePrintDTO> userVoicePrintDTOs) {
        List<UserVoicePrintDTO> printDTOList = new ArrayList<>();
        for (UserVoicePrintDTO voicePrintDTO : userVoicePrintDTOs) {
            log.info("Invocation of saveUserAndVoicePrint() method.");
            User user = userService.saveNewUser(voicePrintDTO.getUser());
            log.info("Invocation of saveNewUser() method from UserService.");
            VoicePrint voicePrint = voicePrintDTO.getVoicePrint();
            voicePrint.setUserHashCode(user.hashCode());
            log.info("setting userHasCode to voicePrint");
            VoicePrint savedVoicePrint = voicePrintService.saveNewVoicePrint(voicePrint);
            log.info("Invocation of saveNewVoicePrint() method from VoicePrintService.");
            printDTOList.add(new UserVoicePrintDTO(user, savedVoicePrint));
        }
        return printDTOList;
    }

    @GetMapping("/{id}")
    public VoicePrintDTO findVoicePrintByUserId(@PathVariable Long id) throws UserException {
        log.info(String.format("Invocation of findVoicePrintByUserId() method with id: %d parameter.", id));
        User user = userService.findUserById(id);
        log.info("Invocation of findUserById() method from userService.");
        int hashCode = user.hashCode();
        VoicePrintDTO voicePrintDTO = voicePrintService.findVoicePrintByUserHashCode(hashCode);
        log.info("Found VoicePrint using findVoicePrintByUserHashCode() method from voicePrintService.");
        return voicePrintDTO;
    }
}
