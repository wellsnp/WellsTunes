/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audio;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wellsnp
 */
//See https://github.com/mpatric/mp3agic
//Testing External Library
public class mp3tags {

  
   private Mp3File mp3File;
   //Add private fields of the tags we want to get/set. 
   private String songName;
   private String artistName;
   private String albumName;
   private int genre;
   private String trackNumber;
   private String songLength;
   
   //
   public mp3tags()  {
      
   }
   
   public boolean checkID3v2(File InputFile){
       try {
         this.mp3File = new Mp3File(InputFile);
       } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
           Logger.getLogger(mp3tags.class.getName()).log(Level.SEVERE, null, ex);
       }
        if(mp3File.hasId3v2Tag()){
           //System.out.println("has ID3v tag");
           ID3v2 ID3v2Tag = mp3File.getId3v2Tag();
           mp3File.getLengthInSeconds();
           this.setArtistName(ID3v2Tag.getAlbumArtist());
           this.setAlbumName(ID3v2Tag.getAlbum());
           this.setSongName(ID3v2Tag.getTitle());
           this.setTrackNumber(ID3v2Tag.getTrack());
           this.setSongLength(convertTimeMMSS(mp3File.getLengthInSeconds()));
           //System.out.println(ID3v2Tag);
           this.setGenre(ID3v2Tag.getGenre());
           //System.out.println("");
       }
        
        return(mp3File.hasId3v2Tag());
       
   }
   
   private String convertTimeMMSS(Long Time){
   //Inputs Time In Seconds
   //Outputs Sting in MM:SS Format    
   String Minutes=Long.toString(Time/60);
   Long SecondsCheck = Time%60;
   String Seconds;
   if(SecondsCheck<10){
       Seconds="0"+Long.toString(Time%60);
   }else
   {
       Seconds=Long.toString(Time%60);
   }
   String TimeString=Minutes+":"+Seconds;
   return TimeString;
   }
   
//Class getters          
    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public int getGenre() {
        return genre;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public String getSongLength() {
        return songLength;
    }

   
//Class setters: This Data Is Set With In The Class So These Are Private   
    private void setSongName(String songName) {
        this.songName = songName;
    }

    private void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    private void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    private void setGenre(int genre) {
        this.genre = genre;
    }

    private void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    private void setSongLength(String songLength) {
        this.songLength = songLength;
    }



}


//Need a Genre LookUp Table To Translate Int into Genre Name;


