package guru.springframework.services;


import guru.springframework.domain.Recipe;
import guru.springframework.repositories.reactive.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import java.io.IOException;

/**
 * Created by jt on 7/3/17.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {


    private final RecipeReactiveRepository recipeReactiveRepository;

    public ImageServiceImpl( RecipeReactiveRepository recipeReactiveRepository) {

        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @Override
    //@Transactional -mongo doesnt support
    public Mono<Void> saveImageFile(String recipeId, MultipartFile file) {
        Mono<Recipe> recipeMono = recipeReactiveRepository.findById(recipeId)
                .map(recipe -> {
                    try {
                        Byte[] byteObjects = new Byte[file.getBytes().length];
                        int i = 0;
                        for (byte b : file.getBytes()) {
                            byteObjects[i++] = b;
                        }
                        recipe.setImage(byteObjects);
                        recipeReactiveRepository.save(recipe);
                        return recipe;
                    } catch (IOException e) {
                        log.error("Error occurred", e);
                        e.printStackTrace();
                        throw new RuntimeException(e.getMessage());
                    }
                });
        recipeReactiveRepository.save(recipeMono.block()).block();


        return Mono.empty();
    }
}
