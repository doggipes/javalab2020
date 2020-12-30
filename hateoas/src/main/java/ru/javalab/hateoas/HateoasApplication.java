package ru.javalab.hateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.javalab.hateoas.models.*;
import ru.javalab.hateoas.repositories.*;

import static java.util.Arrays.asList;

@SpringBootApplication
public class HateoasApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HateoasApplication.class, args);

        AlbumRepository albumRepository = context.getBean(AlbumRepository.class);
        DialogRepository dialogRepository = context.getBean(DialogRepository.class);
        MessageRepository messageRepository = context.getBean(MessageRepository.class);
        MusicRepository musicRepository = context.getBean(MusicRepository.class);
        PhotoRepository photoRepository = context.getBean(PhotoRepository.class);
        UserRepository userRepository = context.getBean(UserRepository.class);

        User doshirak = User.builder()
                .email("123@123.ru")
                .password("123")
                .name("Доширак")
                .build();

        User lapshin = User.builder()
                .email("123@123.ru")
                .password("123")
                .name("Лапшин")
                .build();

        userRepository.saveAll(asList(doshirak, lapshin));

        Album album1 = Album.builder()
                .name("Leto")
                .user(doshirak)
                .build();

        Album album2 = Album.builder()
                .name("Zima")
                .user(lapshin)
                .build();

        albumRepository.saveAll(asList(album1, album2));

        Photo photo1 = Photo.builder()
                .path("/photos")
                .description("selphi")
                .album(album1)
                .build();

        Photo photo2 = Photo.builder()
                .path("/photos")
                .description("zakat")
                .album(album1)
                .build();

        Photo photo3 = Photo.builder()
                .path("/photos")
                .description("rassvet")
                .album(album2)
                .build();

        Photo photo4 = Photo.builder()
                .path("/photos")
                .description("metakarate")
                .album(album2)
                .build();

        photoRepository.saveAll(asList(photo1, photo2, photo3, photo4));

        Music javalabmusic1 = Music.builder()
                .artist("haski")
                .name("panelka")
                .user(doshirak)
                .build();

        Music javalabmusic2 = Music.builder()
                .artist("haski")
                .name("bang-bang")
                .user(doshirak)
                .build();

        Music javalabmusic3 = Music.builder()
                .artist("haski")
                .name("marmelad")
                .user(lapshin)
                .build();

        Music javalabmusic4 = Music.builder()
                .artist("haski")
                .name("revansh")
                .user(lapshin)
                .build();

        musicRepository.saveAll(asList(javalabmusic1, javalabmusic2, javalabmusic3, javalabmusic4));

        Dialog dialog1 = Dialog.builder()
                .name("beseda o pogode")
                .users(asList(doshirak, lapshin))
                .build();

        Dialog dialog2 = Dialog.builder()
                .name("beseda o prognoze pogodi")
                .users(asList(doshirak, lapshin))
                .build();

        dialogRepository.saveAll(asList(dialog1, dialog2));

        Message message1 = Message.builder()
                .dialog(dialog1)
                .user(doshirak)
                .text("segodnya bil dozhd")
                .build();

        Message message2 = Message.builder()
                .dialog(dialog1)
                .user(lapshin)
                .text("da mne ponravilos")
                .build();

        Message message3 = Message.builder()
                .dialog(dialog2)
                .user(doshirak)
                .text("segodnya obechali solnce")
                .build();

        Message message4 = Message.builder()
                .dialog(dialog2)
                .user(lapshin)
                .text("da mne ne ponravilos")
                .build();

        messageRepository.saveAll(asList(message1, message2, message3, message4));
    }
}
