package edu.wat.pl.blog.title;

import edu.wat.pl.blog.post.Post;
import edu.wat.pl.blog.title.dto.Titles;
import edu.wat.pl.blog.title.repository.TitlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitlesService {

    @Autowired
    TitlesRepository titlesRepository;

    public void saveTitle(Post post) {
        titlesRepository.save(new Titles(post.getTitle()));
    }

    public List<Titles> findAllTitles() {
        return titlesRepository.findAll();
    }

}
