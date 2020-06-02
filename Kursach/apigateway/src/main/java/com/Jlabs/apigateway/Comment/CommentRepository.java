package com.Jlabs.ApiGateway.Comment;

import com.Jlabs.ApiGateway.ResData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(value = "comment")
public interface CommentRepository {

    @RequestMapping(path = "/comment", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Iterable<Comment>> getAll();

    @RequestMapping(path = "/comment/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResData<Optional<Comment>> getById(@PathVariable int id);

    @RequestMapping(path="/comment", method = RequestMethod.POST)
    public @ResponseBody
    ResData<String> addComment(@RequestParam(value = "customerId", required = true) Integer customerId,
                                @RequestParam(value = "content", required = true) String content,
                                @RequestParam(value = "clothesId", required = true) Integer clothesId);

    @RequestMapping(path = "/comment/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResData<String> deleteComment(@PathVariable Integer id);

    @RequestMapping(path="/comment/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    ResData<String> updateComment(@PathVariable int id,
                                   @RequestParam Integer customerId,
                                   @RequestParam String content,
                                   @RequestParam Integer clothesId);
}