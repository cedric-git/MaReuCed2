package com.crea2dev.mareuced.Service;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class DummyMeetingGenerator {


    private static ArrayList list;
    public static void main(String[] args) {
        ArrayList <String> list = new ArrayList<>();
        list.add("zoe@gmail.com");
        list.add("soph@gr.com");
        list.add("ced@gmail.com");
        list.add("ninja@hotmail.com");

//        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
//        ListView listView = (ListView) findViewById(R.id.participant_ListView);
//        listView.setAdapter(itemsAdapter);
        System.out.println(list);
    }




    ListView mListView;
    String[] prenoms = new String[]{
            "Antoine", "Benoit", "Cyril", "David", "Eloise", "Florent",
            "Gerard", "Hugo", "Ingrid", "Jonathan", "Kevin", "Logan",
            "Mathieu", "Noemie", "Olivia", "Philippe", "Quentin", "Romain",
            "Sophie", "Tristan", "Ulric", "Vincent", "Willy", "Xavier",
            "Yann", "Zoé"
    };


    String commaSeparated = "bob@gmail.com, ninja@hotmail.com, barnabe@free.fr, bob@gmail.com, ninja@hotmail.com, barnabe@free.fr";
    List<String> items = Arrays.asList(commaSeparated.split("\\s*,\\s*"));
//    System.out.println(items);
String nL1 = System.getProperty("line.separator"); // pour 1 interligne
    String mot1="salut";
    String mot2="dalby";
    String Newligne=System.getProperty("line.separator");
    String resultat=mot1+Newligne+mot2;

    public class Test {
        public void main(String[] args) {
            String sampleString = "Cat,Dog,Elephant";
            String[] items = sampleString.split(",");
            List<String> itemList = Arrays.asList(items);
            System.out.println(itemList);

        }
    }


    private static List<MeetingModel> DUMMY_MEETINGS = Arrays.asList(

            new MeetingModel("3 Reunion 3", "15:00PM", "Salle 7", "zoe@gmail.com"),
            new MeetingModel("1 Reunion 1", "14:00PM", "Salle 3", "bob@gmail.com"),
            new MeetingModel("2 Reunion 2", "16:00PM", "Amphi", "ced@gmail.com")

//            new MeetingModel("Strategie", "16H20", "Salle 7", "zoe@gmail.com, luigi@yahoo.com", items),
//                        new MeetingModel("Strategie", "16H20", "Salle 7", "zoe@gmail.com \r\n, luigi@yahoo.com", list)


    );

    static List<MeetingModel> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}