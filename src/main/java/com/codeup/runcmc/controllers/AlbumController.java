package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.AlbumResponse;
import com.codeup.runcmc.services.RestTemplateAlbumRequester;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AlbumController {


//    @GetMapping("/album")
//    @ResponseBody
//    public AlbumResponse requestToken() {
//        return RestTemplateAlbumRequester.requestAccessAlbum("BQCfY2B92Uf6Aty9g69V9JF2kntWmaKo48TfhlYWbrOuoJtJJ2ipmfzMlnFblV0Qh3hhaE-1Qk5di9S2pkA", "4aawyAB9vmqN3uQ7FjRGTy");
//    }

    @GetMapping("/album")
    public String requestAlbum() {
        AlbumResponse albumResponse = RestTemplateAlbumRequester.requestAccessAlbum("AUTH_TOKEN", "SPOTIFY_ALBUM_ID");

        return "album.html";

    }


}
//