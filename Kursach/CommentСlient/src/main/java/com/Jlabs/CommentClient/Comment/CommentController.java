package com.Jlabs.CommentClient.Comment;

import com.Jlabs.CommentClient.ResData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping(path="/comment")
public class CommentController {

    @Autowired
    private CommentRepository repository;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Comment>> getAll() {
        return new ResData<Iterable<Comment>>(repository.findAll(), instanceId);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody ResData<Optional<Comment>> getById(@PathVariable int id) {
        return new ResData<Optional<Comment>>(repository.findById(id), instanceId);
    }

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody ResData<String> addComment(@RequestParam(value = "customerId", required = true) Integer customerId,
                                                     @RequestParam(value = "content", required = true) String content,
                                                     @RequestParam(value = "clothesId", required = true) Integer clothesId) throws ParseException {
        Comment instance = new Comment();
        instance.setCustomerId(customerId);
        instance.setContent(content);
        instance.setClothesId(clothesId);
        repository.save(instance);
        return new ResData<String>("Added comment", instanceId);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResData<String> deleteComment(@PathVariable int id) {
        repository.deleteById(id);
        return new ResData<String>("Deleted", instanceId);
    }


    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResData<String> updateComment(@PathVariable int id,
                                                        @RequestParam Integer customerId,
                                                        @RequestParam String content,
                                                        @RequestParam Integer clothesId) throws ParseException {
        Comment instance = repository.getById(id);
        instance.setCustomerId(customerId);
        instance.setContent(content);
        instance.setClothesId(clothesId);
        repository.save(instance);
        return new ResData<String>("Updated", instanceId);
    }
}