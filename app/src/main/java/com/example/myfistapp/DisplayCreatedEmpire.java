package com.example.myfistapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.gson.Gson;
import org.apache.commons.jexl3.*;

public class DisplayCreatedEmpire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_created_empire);

        TextView EmpireName = findViewById(R.id.EmpireName);
        TextView Name = findViewById(R.id.Name);
        TextView Bio = findViewById(R.id.Bio);
        TextView NameList = findViewById(R.id.NameList);
        TextView Cityset = findViewById(R.id.Cityset);
        TextView AdvisorVoice = findViewById(R.id.AdvisorVoice);
        TextView Shipset = findViewById(R.id.Shipset);
        TextView Ethics = findViewById(R.id.Ethics);
        TextView Traits = findViewById(R.id.traits);
        TextView Authority = findViewById(R.id.Authority);
        TextView Civics = findViewById(R.id.Civics);
        EmpireObject newEmpire = new EmpireObject();
        generateRandomEmpire(newEmpire);
        String[] EmpireAttributes = newEmpire.getEmpireAttributes();
        EmpireName.setText(newEmpire.toString());
        Name.setText(EmpireAttributes[0]);
        Bio.setText(EmpireAttributes[1]);
        NameList.setText(EmpireAttributes[2]);
        Cityset.setText(EmpireAttributes[3]);
        AdvisorVoice.setText(EmpireAttributes[4]);
        Shipset.setText(EmpireAttributes[5]);
        Ethics.setText(createMultiLineText(newEmpire.getEmpireEthics()));
        Traits.setText(createMultiLineText(newEmpire.getEmpireTraits()));
        Authority.setText(newEmpire.getAuthority());
        Civics.setText(createMultiLineText(newEmpire.getEmpireCivics()));
    }

    public void generateRandomEmpire(EmpireObject newEmpire){
        String [] choiceArray;
        choiceArray = getResources().getStringArray(R.array.Names);
        String randomName = choiceArray[new Random().nextInt(choiceArray.length)];
        choiceArray = getResources().getStringArray(R.array.EmpireNames);
        String randomEmpireName = choiceArray[new Random().nextInt(choiceArray.length)];
        choiceArray = getResources().getStringArray(R.array.Biographies);
        String randomBio = choiceArray[new Random().nextInt(choiceArray.length)];
        choiceArray = getResources().getStringArray(R.array.NameLists);
        String randomNameList = choiceArray[new Random().nextInt(choiceArray.length)];
        choiceArray = getResources().getStringArray(R.array.CitySet);
        String randomCityset = choiceArray[new Random().nextInt(choiceArray.length)];
        choiceArray = getResources().getStringArray(R.array.AdvisorVoices);
        String randomAdvisorVoice = choiceArray[new Random().nextInt(choiceArray.length)];
        choiceArray = getResources().getStringArray(R.array.Shipsets);
        String randomShipset = choiceArray[new Random().nextInt(choiceArray.length)];
        String[] randomEthics = generateEthics();
        TraitModel.TraitObject[] randomTraits = generateTraits();
        String randomAuthority = generateAuthority(randomEthics);
        CivicModel.CivicObject[] randomCivics = generateCivics(randomAuthority,randomEthics);
        newEmpire.setEmpireAttributes(randomName,randomEmpireName,randomBio,randomNameList,randomCityset,randomAdvisorVoice,randomShipset,randomEthics,randomTraits,randomAuthority,randomCivics);

    }
    public String createMultiLineText(String[] text){
        int i = 0;
        String MultiLineText = new String();
        while(i<text.length && text[i] != null){
            MultiLineText+=text[i]+'\n';
            i++;
        }
        return MultiLineText;
    }
    public String[] generateEthics(){
        String[] Ethics = new String[3];
        int place = 0;
        int points = 3;
        boolean[] taken = new boolean[4];
        while(points > 0){
            int chosenEthos = new Random().nextInt(4);
            if(chosenEthos == 0 && !taken[0]){
                int ethosStrength;
                if (points>1) ethosStrength = new Random().nextInt(4);
                else ethosStrength = new Random().nextInt(2);
                taken[0] = true;
                switch(ethosStrength) {
                    case 3:
                        points -= 2;
                        Ethics[place] = "Fanatic Authoritarian";
                        break;
                    case 2:
                        points -= 2;
                        Ethics[place] = "Fanatic Egalitarian";
                        break;
                    case 1:
                        points -= 1;
                        Ethics[place] = "Authoritarian";
                        break;
                    case 0:
                        points -= 1;
                        Ethics[place] = "Egalitarian";
                        break;
                }
                place+=1;
            }
            else if(chosenEthos == 1 && !taken[1]){
                int ethosStrength;
                if (points>1) ethosStrength = new Random().nextInt(4);
                else ethosStrength = new Random().nextInt(2);
                taken[1] = true;
                switch(ethosStrength){
                    case 3: points -=2;
                            Ethics[place] = "Fanatic Xenophobe";
                            break;
                    case 2: points -=2;
                            Ethics[place] = "Fanatic Xenophile";
                            break;
                    case 1: points -=1;
                            Ethics[place] = "Xenophobe";
                            break;
                    case 0: points -=1;
                            Ethics[place] = "Xenophile";
                            break;
                }
                place+=1;
            }
            else if(chosenEthos == 2 && !taken[2]){
                int ethosStrength;
                if (points>1) ethosStrength = new Random().nextInt(4);
                else ethosStrength = new Random().nextInt(2);
                taken[2] = true;
                switch(ethosStrength){
                    case 3: points -=2;
                            Ethics[place] = "Fanatic Militarist";
                            break;
                    case 2: points -=2;
                            Ethics[place] = "Fanatic Pacifist";
                            break;
                    case 1: points -=1;
                            Ethics[place] = "Militarist";
                            break;
                    case 0: points -=1;
                            Ethics[place] = "Pacifist";
                            break;
                }
                place+=1;
            }
            else if(chosenEthos == 3 && !taken[3]){
                int ethosStrength;
                if (points>1) ethosStrength = new Random().nextInt(4);
                else ethosStrength = new Random().nextInt(2);
                taken[3] = true;
                switch(ethosStrength){
                    case 3: points -=2;
                            Ethics[place] = "Fanatic Materialist";
                            break;
                    case 2: points -=2;
                            Ethics[place] = "Fanatic Spiritualist";
                            break;
                    case 1: points -=1;
                            Ethics[place] = "Materialist";
                            break;
                    case 0: points -=1;
                            Ethics[place] = "Spiritualist";
                            break;
                }
                place+=1;
            }
        }
        return Ethics;
    }

    public TraitModel.TraitObject[] generateTraits(){
        TraitModel.TraitObject[] chosenTraits = new TraitModel.TraitObject[5];
        int place =0;
        int points = 2;
        TraitModel traitModel;
        String myJson=inputStreamToString(this.getResources().openRawResource(R.raw.traits));
        Gson gson = new Gson();
        traitModel = gson.fromJson(myJson,TraitModel.class);
        TraitModel.TraitObject[] traits = traitModel.getTraits();
        for(TraitModel.TraitObject trait: traits){
            for (String IncompatibleTrait: trait.showIncompatibleTraits()){
               trait.addIncompatibleTrait(findTrait(traits,IncompatibleTrait));
            }
        }
        Set<TraitModel.TraitObject> AvailableTraits = new HashSet<>(Arrays.asList(traits));
        int i = 0;
        while(i<5){
            int random = new Random().nextInt(9);
            if(random==8) break;
            random = new Random().nextInt(AvailableTraits.size());
            Iterator<TraitModel.TraitObject> it = AvailableTraits.iterator();
            for (int j = 0; j < random; j++) {
                it.next();
            }
            TraitModel.TraitObject newTrait = it.next();
            if(newTrait.getPointValue()+points < 0) continue;
            chosenTraits[place] = newTrait;
            points += newTrait.getPointValue();
            AvailableTraits.remove(newTrait);
            if(newTrait.getIncompatibleTraits() != null) {
                for (TraitModel.TraitObject SetTrait : newTrait.getIncompatibleTraits()) {
                    if (AvailableTraits.contains(SetTrait)) AvailableTraits.remove(SetTrait);
                }
            }
            place+=1;
            i++;
        }
        return chosenTraits;

    }
    public TraitModel.TraitObject findTrait(TraitModel.TraitObject[] traits, String IncTrait){
        for(TraitModel.TraitObject trait: traits){
            if (IncTrait.equals(trait.toString())){
                return trait;
            }
        }
        return null;
    }

    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }
    public String generateAuthority(String[] ethics){
        List<String> Ethics = Arrays.asList(ethics);
        ArrayList<String> Authorities = new ArrayList<String>(Arrays.asList("Democratic", "Oligarchic", "Dictatorial","Imperial"));
        if(Ethics.contains("Authoritarian")||Ethics.contains("Fanatic Authoritarian")){
            Authorities.remove("Democratic");
        }
        else if (Ethics.contains("Fanatic Authoritarian")){
            Authorities.remove("Democratic");
            Authorities.remove("Oligarchic");
        }
        else if(Ethics.contains("Egalitarian")){
            Authorities.remove("Imperial");
            Authorities.remove("Dictatorial");
        }
        int random = new Random().nextInt(Authorities.size());
        return Authorities.get(random);
    }
    public CivicModel.CivicObject[] generateCivics(String authority, String[] ethics){
        CivicModel.CivicObject[] chosenCivics =  new CivicModel.CivicObject[2];
        CivicModel civicModel;
        List<String>Ethics = Arrays.asList(ethics);
        String myJson=inputStreamToString(this.getResources().openRawResource(R.raw.civics));
        Gson gson = new Gson();
        civicModel = gson.fromJson(myJson,CivicModel.class);
        List<CivicModel.CivicObject> availableCivics = new ArrayList<>(Arrays.asList(civicModel.getCivics()));
        for(int i= 0; i<availableCivics.size();i++ ){
            CivicModel.CivicObject civic = availableCivics.get(i);
            List<String>RequiredAuthority = new ArrayList<>(Arrays.asList(civic.getRequiredAuthority()));
            List<String>IncompatibleAuthority = Arrays.asList(civic.getIncompatibleAuthority());
            String RequiredEthics = civic.getRequiredEthics();
            List<String>IncompatibleEthics = Arrays.asList(civic.getIncompatibleEthics());
            if(RequiredAuthority.size()>0 && !RequiredAuthority.contains(authority)){
                availableCivics.remove(civic);
                i--;
                continue;
            }
            if(IncompatibleAuthority.contains(authority)){
                availableCivics.remove(civic);
                --i;
                continue;
            }
            if(RequiredEthics.length()>0 && !checkEthicsCompatibility(Ethics,RequiredEthics)){
                availableCivics.remove(civic);
                --i;
                continue;
            }
            List<String> CommonElements = new ArrayList<>(IncompatibleEthics);
            CommonElements.retainAll(Ethics);
            if(IncompatibleEthics.size()>0 && (CommonElements.size()!=0)){
                availableCivics.remove(civic);
                --i;
            }
        }
        int random = new Random().nextInt(availableCivics.size());
        chosenCivics[0] = availableCivics.get(random);
        availableCivics.remove(random);
        removeIncompatibleCivics(availableCivics,chosenCivics[0]);
        random = new Random().nextInt(availableCivics.size());
        chosenCivics[1] = availableCivics.get(random);
        return chosenCivics;
    }
    public void removeIncompatibleCivics(List<CivicModel.CivicObject> availableCivics, CivicModel.CivicObject civic){
        List<String> IncompatibleCivics = new ArrayList<>(Arrays.asList(civic.getIncompatibleCivics()));
        for(int i =0; i<availableCivics.size();i++){
            if(IncompatibleCivics.contains(availableCivics.get(i).toString())){
                availableCivics.remove(i);
            }
        }
    }
    public boolean checkEthicsCompatibility(List<String> Ethics, String RequiredEthics){
        String[] allEthics = {"Fanatic Militarist","Militarist","Fanatic Pacifist","Pacifist","Fanatic Authoritarian","Authoritarian","Fanatic Egalitarian","Egalitarian",
                "Fanatic Xenophile","Xenophile","Fanatic Xenophobe","Xenophobe","Fanatic Materialist","Materialist","Fanatic Spiritualist","Spiritualist"};
        for(String Ethic:allEthics){
            String newEthic;
            if(Ethic.contains("Fanatic ")) newEthic = Ethic.replace("Fanatic ","")+"2";
            else newEthic = Ethic+"1";
            if(RequiredEthics.contains(newEthic) && Ethics.contains(Ethic)) RequiredEthics = RequiredEthics.replaceAll(newEthic,"true");
            else if(RequiredEthics.contains(newEthic)) RequiredEthics = RequiredEthics.replaceAll(newEthic,"false");
        }
        JexlEngine jexl = new JexlBuilder().create();
        JexlExpression jExpression = jexl.createExpression(RequiredEthics);
        JexlContext context = new MapContext();
        context.set("true", true);
        context.set("false", false);
        Boolean result = (Boolean) jExpression.evaluate(context);
        return result;
    }
}
