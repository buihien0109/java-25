package com.example.movieapp.controller;

import com.example.movieapp.entity.*;
import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.model.response.TokenConfirmMessageResponse;
import com.example.movieapp.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MovieService movieService;
    private final BlogService blogService;
    private final ReviewService reviewService;
    private final EpisodeService episodeService;
    private final AuthService authService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Movie> listPhimBo = movieService.getMoviesByType(MovieType.PHIM_BO, true, 1, 6).getContent();
        List<Movie> listPhimLe = movieService.getMoviesByType(MovieType.PHIM_LE, true, 1, 6).getContent();
        List<Movie> listPhimChieuRap = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, 1, 6).getContent();
        model.addAttribute("listPhimBo", listPhimBo);
        model.addAttribute("listPhimLe", listPhimLe);
        model.addAttribute("listPhimChieuRap", listPhimChieuRap);
        return "web/index";
    }

    // /phim-bo?page=1&pageSize=12
    @GetMapping("/phim-bo")
    public String getPhimBoPage(Model model,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/phim-bo";
    }

    @GetMapping("/phim-le")
    public String getPhimLePage(Model model,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_LE, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(Model model,
                                      @RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/phim-chieu-rap";
    }

    // /phim/1/chua-te-nhung-chiec-nhan
    @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailsPage(Model model,
                                      @PathVariable Integer id,
                                      @PathVariable String slug) {
        Movie movie = movieService.getMovieDetails(id, slug);
        List<Review> reviews = reviewService.getReviewsByMovieId(id);
        List<Episode> episodes = episodeService.getEpisodesActiveByMovie(id);
        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviews);
        model.addAttribute("episodes", episodes);
        return "web/chi-tiet-phim";
    }

    @GetMapping("/xem-phim/phim/{id}/{slug}")
    public String getMovieStreamingDetailsPage(Model model,
                                               @PathVariable Integer id,
                                               @PathVariable String slug,
                                               @RequestParam String tap) {
        Movie movie = movieService.getMovieDetails(id, slug);
        List<Review> reviews = reviewService.getReviewsByMovieId(id);
        List<Episode> episodes = episodeService.getEpisodesActiveByMovie(id);
        Episode currentEpisode = episodeService.getEpisodeByDisplayOrder(id, tap);
        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviews);
        model.addAttribute("episodes", episodes);
        model.addAttribute("currentEpisode", currentEpisode);
        return "web/xem-phim";
    }

    @GetMapping("/tin-tuc")
    public String getBlogPage(Model model,
                              @RequestParam(required = false, defaultValue = "1") int page,
                              @RequestParam(required = false, defaultValue = "10") int pageSize) {
        Page<Blog> pageData = blogService.getBlogs(true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/tin-tuc";
    }

    @GetMapping("/tin-tuc/{id}/{slug}")
    public String getBlogDetailsPage(Model model,
                                     @PathVariable Integer id,
                                     @PathVariable String slug) {
        Blog blog = blogService.getBlogDetails(id, slug);
        model.addAttribute("blog", blog);
        return "web/chi-tiet-tin-tuc";
    }

    @GetMapping("/dang-nhap")
    public String loginPage(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user != null) {
            return "redirect:/";
        }
        return "web/dang-nhap";
    }

    @GetMapping("/dang-ky")
    public String registerPage(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user != null) {
            return "redirect:/";
        }
        return "web/dang-ky";
    }

    // http://localhost:8081/xac-thuc-tai-khoan?token=09981021-4616-4e0c-85b9-4dc74022d3d7
    @GetMapping("/xac-thuc-tai-khoan")
    public String verifyAccountPage(@RequestParam String token, Model model) {
        TokenConfirmMessageResponse response = authService.verifyAccount(token);
        model.addAttribute("response", response);
        model.addAttribute("token", token);
        return "web/xac-thuc-tai-khoan";
    }

    @GetMapping("/quen-mat-khau")
    public String forgotPasswordPage(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user != null) {
            return "redirect:/";
        }
        return "web/quen-mat-khau";
    }
}
