package org.sopt;

import java.util.List;

import org.sopt.client.InputReader;
import org.sopt.client.OutputView;
import org.sopt.controller.PostController;
import org.sopt.entity.Post;
import org.sopt.repository.PostRepository;
import org.sopt.response.ApiResponse;
import org.sopt.response.ErrorCode;
import org.sopt.response.SuccessCode;
import org.sopt.service.PostService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
