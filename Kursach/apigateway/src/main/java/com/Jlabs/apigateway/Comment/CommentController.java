package com.Jlabs.ApiGateway.Comment;

import com.Jlabs.ApiGateway.ResData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@RequestMapping(path="/comment")
@Controller
public class CommentController {

    @Autowired
    private CommentRepository repository;

    @RequestMapping(path = "" , method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Comment>> getAll() {
        return repository.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Optional<Comment>> getById(@PathVariable int id) {
        return repository.getById(id);
    }

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody
    ResData<String> addComment(@RequestParam(value = "customerId", required = true) Integer customerId,
                                @RequestParam(value = "content", required = true) String content,
                                @RequestParam(value = "clothesId", required = true) Integer clothesId) {
        return repository.addComment(customerId, content, clothesId);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResData<String> deleteComment(@PathVariable Integer id) {
        return repository.deleteComment(id);
    }


    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    ResData<String> updateComment(@PathVariable int id,
                                   @RequestParam Integer customerId,
                                   @RequestParam String content,
                                   @RequestParam Integer clothesId) throws ParseException {
        return repository.updateComment(id, customerId, content, clothesId);
    }
}