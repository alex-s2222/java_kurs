package com.main.app.controllers;

import com.main.app.models.Task;
import com.main.app.repo.TaskRepository;
import com.main.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Objects;

@Controller
public class ViewTaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable(value = "id") long id) {
        model.addAttribute("task", taskRepository.findById(id).get());
        return "task/task-view";
    }

    @GetMapping("/edit-task/{id}")
    public String edit(Model model, @PathVariable(value = "id") long id) {
        Task task = taskRepository.findById(id).get();
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), task.author)) {
            model.addAttribute("task", task);
        }else{
            return "redirect:/view/"+id;
        }
        return "task/task-edit";
    }

    @PostMapping("/edit-task/{id}")
    public String postEdit(Model model,
                           @PathVariable(value = "id") long id,
                           @RequestParam String name,
                           @RequestParam String fullText,
                           @RequestParam String executor,
                           @RequestParam String observer,
                           @RequestParam String deadline){
        Task task = taskRepository.findById(id).get();

        if (userRepository.findByUsername(executor) == null && !(executor.equals(""))) {
            model.addAttribute("error", "Такого пользователя не существует");
            return "task/task-edit";
        }
        if (userRepository.findByUsername(observer) == null && !(observer.equals(""))) {
            model.addAttribute("error", "Такого наблюдателя не существует");
            return "task/task-edit";
        }
        if (deadline.equals("")) {
            model.addAttribute("error", "Не правильное время");
            return "task/task-edit";
        }
        task.setName(name);
        task.setFullText(fullText);
        task.setStatus("Изменено "+new Date());
        task.setDeadline(java.sql.Date.valueOf(deadline));
        task.setObserver(observer);
        task.setExecutor(executor);
        taskRepository.save(task);
        return "redirect:/view/"+id;
    }

    @GetMapping("/close-wait/{id}")
    public String closeTask(Model model, @PathVariable(value = "id") long id){
        Task task = taskRepository.findById(id).get();
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), task.observer)){
            task.setStatus("Закрыта");
            taskRepository.save(task);
            return "redirect:/view/"+id;
        }
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), task.executor)){
            task.setStatus("Ожидает закрытия");
            taskRepository.save(task);
            return "redirect:/view/"+id;
        }

        task.setStatus("Закрыта автором");
        taskRepository.save(task);
        return "redirect:/view/"+id;

    }

}
