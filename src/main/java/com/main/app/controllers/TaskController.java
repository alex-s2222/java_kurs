package com.main.app.controllers;

import com.main.app.models.Task;
import com.main.app.repo.TaskRepository;
import com.main.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/task")
    public String task(Model model) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Iterable<Task> tasks = taskRepository.findAllByAuthor(currentUser);
        Iterable<Task> tasksObserver = taskRepository.findAllByObserver(currentUser);
        Iterable<Task> tasksExec = taskRepository.findAllByExecutor(currentUser);
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksObserver", tasksObserver);
        model.addAttribute("tasksExec", tasksExec);
        return "task/task";
    }

    @GetMapping("/task/author")
    public String taskAuthor(Model model) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Iterable<Task> tasks = taskRepository.findAllByAuthor(currentUser);
        model.addAttribute("tasks", tasks);
        return "task/task-author";
    }

//    @GetMapping("/task/executor")
//    public String taskExec(Model model) {
//        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
//        Iterable<Task> tasks = taskRepository.findAllByExecutor(currentUser);
//        model.addAttribute("tasks", tasks);
//        return "task/task-exec";
//    }

    @GetMapping("/task/observer")
    public String taskObserver(Model model) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Iterable<Task> tasks = taskRepository.findAllByObserver(currentUser);
        model.addAttribute("tasks", tasks);
        return "task/task-observ";
    }

    @GetMapping("/task/wait")
    public String waitAnswer(Model model){
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Iterable<Task> tasks = taskRepository.findByStatusAndObserver("Ожидает закрытия",currentUser);
        Iterable<Task> tasksExec = taskRepository.findByStatusAndExecutor("Ожидает закрытия",currentUser);
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskExec", tasksExec);
        return "task/task-wait";
    }

    @GetMapping("/task/close")
    public String taskClose(Model model){
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Iterable<Task> tasks = taskRepository.findByStatusAndExecutor("Закрыта",currentUser);
        Iterable<Task> tasksCloseByAuthor = taskRepository.findByStatusAndExecutor("Закрыта автором",currentUser);
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCloseByAuthor", tasksCloseByAuthor);
        return "task/task-close";
    }

}
