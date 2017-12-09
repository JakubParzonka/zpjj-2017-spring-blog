package edu.wat.pl.blog.favorite.controller;

import edu.wat.pl.blog.favorite.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class FavortiesController {

    @Autowired
    private FavoriteService favoriteService;


}
