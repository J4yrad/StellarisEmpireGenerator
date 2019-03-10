package stellaris.empire.generator;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import org.apache.commons.jexl3.*;

public class DisplayCreatedEmpire extends AppCompatActivity {
    boolean has_utopia = false;
    boolean has_synthetic_dawn = false;
    boolean has_humanoids = false;
    boolean has_apocalypse = false;
    boolean has_plantoids = false;
    boolean has_megacorp = false;
    boolean HiveMind = false;
    boolean MachineEmpire = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_created_empire);
        ShowEmpire();
    }

    public void generateRandomEmpire(EmpireObject newEmpire){
        String [] choiceArray;
        String[] randomEthics = generateEthics();
        choiceArray = getResources().getStringArray(R.array.Names);
        String randomName = choiceArray[new Random().nextInt(choiceArray.length)];
        choiceArray = getResources().getStringArray(R.array.Biographies);
        String randomBio = choiceArray[new Random().nextInt(choiceArray.length)];
        choiceArray = getResources().getStringArray(R.array.NameLists);
        String randomNameList = choiceArray[new Random().nextInt(choiceArray.length)];
        choiceArray = getResources().getStringArray(R.array.CitySet);
        String randomCityset = choiceArray[new Random().nextInt(choiceArray.length)];
        choiceArray = getResources().getStringArray(R.array.AdvisorVoices);        String randomAdvisorVoice = choiceArray[new Random().nextInt(choiceArray.length)];
        choiceArray = getResources().getStringArray(R.array.Shipsets);
        String randomShipset = choiceArray[new Random().nextInt(choiceArray.length)];
        TraitModel.TraitObject[] randomTraits = generateTraits();
        String randomAuthority = generateAuthority(randomEthics);
        CivicModel.CivicObject[] randomCivics = generateCivics(randomAuthority,randomEthics);
        HomeworldObject randomHomeworld = generateHomeworld(randomCivics);
        String randomEmpireName = generateEmpireName(randomAuthority,randomCivics,randomName,randomHomeworld.getStar(),randomHomeworld.getName());

        String[] randomPortrait = generatePortrait();
        newEmpire.setEmpireAttributes(randomName,randomEmpireName,randomBio,randomNameList,randomCityset,randomAdvisorVoice,randomShipset,
                randomEthics,randomTraits,randomAuthority,randomCivics, randomHomeworld, randomPortrait);


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
        if(has_utopia || has_synthetic_dawn){
            int random = new Random().nextInt(100);
            if(random < 14){
                Ethics[1] = "Gestalt Consciousness";
                random = new Random().nextInt(2);
                if (random == 1 && has_synthetic_dawn) MachineEmpire = true;
                else if (random == 1 && !has_synthetic_dawn) HiveMind = true;
                else if (random == 0 && !has_utopia) MachineEmpire = true;
                else HiveMind = true;
                return Ethics;
            }
        }
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
        String[] HiveMindIncomaptibleTraits = {"Conformists","Conservationist","Deviants","Wasteful"};
        TraitModel traitModel;
        String myJson;
        if(MachineEmpire){
            myJson=inputStreamToString(this.getResources().openRawResource(R.raw.synth_traits));
        }
        else  myJson=inputStreamToString(this.getResources().openRawResource(R.raw.traits));
        Gson gson = new Gson();
        traitModel = gson.fromJson(myJson,TraitModel.class);
        List<TraitModel.TraitObject> availableTraits = new ArrayList<>(Arrays.asList(traitModel.getTraits()));
        if(HiveMind) removeIncompatibleTraits(availableTraits,HiveMindIncomaptibleTraits);
        int i = 0;
        while(i<5){
            int random = new Random().nextInt(availableTraits.size());
            if (availableTraits.get(random).getPointValue() + points >= 0){
                TraitModel.TraitObject trait = availableTraits.get(random);
                chosenTraits[i] = trait;
                removeIncompatibleTraits(availableTraits,trait.showIncompatibleTraits());
                points += trait.getPointValue();
                availableTraits.remove(trait);
            }
            else continue;
            random = new Random().nextInt(9);
            if (random == 8) break;
            i++;

        }
        String traitJson = gson.toJson(chosenTraits);
        TraitModel.TraitObject[] mytraitmodel = gson.fromJson(traitJson,TraitModel.TraitObject[].class);
        return chosenTraits;

    }
    public void removeIncompatibleTraits(List<TraitModel.TraitObject> availibleTraits, String[] incTraits){
        List<String>incompatibleTraits = Arrays.asList(incTraits);
        for(int i=0;i<availibleTraits.size();i++){
            if (incompatibleTraits.contains(availibleTraits.get(i).toString())) availibleTraits.remove(availibleTraits.get(i));
        }

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
        if(MachineEmpire) return "Machine Intelligence";
        else if(HiveMind) return "Hive Mind";
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
        if(has_megacorp && !Ethics.contains("Fanatic Authoritarian") && !Ethics.contains("Fanatic Egalitarian")) Authorities.add("Corporate");
        int random = new Random().nextInt(Authorities.size());
        return Authorities.get(random);
    }
    public CivicModel.CivicObject[] generateCivics(String authority, String[] ethics){
        CivicModel.CivicObject[] chosenCivics =  new CivicModel.CivicObject[2];
        CivicModel civicModel;
        String[] utopiaCivics = {"Fanatic Purifiers","Mechanist","Syncretic Evolution"};
        String[] apocCivics = {"Life Seeded","Post Apocalyptic","Barbaric Despoilers"};
        String[] megacorpCivics={"Byzantine Bureaucracy","Merchant Guilds","Shared Burdens"};
        String myJson;
        List<String>Ethics = Arrays.asList(ethics);
        if(MachineEmpire) myJson = inputStreamToString(this.getResources().openRawResource(R.raw.synth_civics));
        else if(HiveMind) myJson = inputStreamToString(this.getResources().openRawResource(R.raw.hive_mind_civics));
        else if(authority == "Corporate") myJson = inputStreamToString(this.getResources().openRawResource(R.raw.corporate_civics));
        else myJson=inputStreamToString(this.getResources().openRawResource(R.raw.civics));
        Gson gson = new Gson();
        civicModel = gson.fromJson(myJson,CivicModel.class);
        List<CivicModel.CivicObject> availableCivics = new ArrayList<>(Arrays.asList(civicModel.getCivics()));
        if(!has_utopia&&!MachineEmpire&&!(authority=="Corporate"))removeIncompatibleCivics(availableCivics,utopiaCivics);
        if(!has_apocalypse&&!MachineEmpire&&!HiveMind&&!(authority=="Corporate"))removeIncompatibleCivics(availableCivics,apocCivics);
        if(!has_megacorp&&!MachineEmpire&&!HiveMind)removeIncompatibleCivics(availableCivics,megacorpCivics);
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
        String civicJson = gson.toJson(chosenCivics);
        return chosenCivics;
    }
    public void removeIncompatibleCivics(List<CivicModel.CivicObject> availableCivics, CivicModel.CivicObject civic){
        List<String> IncompatibleCivics = new ArrayList<>(Arrays.asList(civic.getIncompatibleCivics()));
        for(int i =0; i<availableCivics.size();i++){
            if(IncompatibleCivics.contains(availableCivics.get(i).toString())){
                availableCivics.remove(i);
                i--;
            }
        }
    }
    public void removeIncompatibleCivics(List<CivicModel.CivicObject> availableCivics, String[] incCivics){
        List<String> IncompatibleCivics = new ArrayList<>(Arrays.asList(incCivics));
        for(int i =0; i<availableCivics.size();i++){
            if(IncompatibleCivics.contains(availableCivics.get(i).toString())){
                availableCivics.remove(i);
                i--;
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
    public HomeworldObject generateHomeworld(CivicModel.CivicObject[] civics){
        String[] PlanetTypes = getResources().getStringArray(R.array.HomeworldTypes);
        String[] StarNames = getResources().getStringArray(R.array.StarNames);
        String[] Systems = getResources().getStringArray(R.array.SpecialSystems);
        String[] PlanetNames = getResources().getStringArray(R.array.PlanetNames);
        String HomeworldType = PlanetTypes[new Random().nextInt(PlanetTypes.length)];
        String PlanetName = PlanetNames[new Random().nextInt(PlanetNames.length)];
        String StarName =  StarNames[new Random().nextInt(StarNames.length)];
        String SystemType = Systems[new Random().nextInt(Systems.length)];
        for(CivicModel.CivicObject civic: civics){
            if(civic.toString().equals("Life Seeded")) HomeworldType = "Gaia";
            else if(civic.toString().equals("Post Apocalyptic")||civic.toString().equals("Determined Exterminator")) HomeworldType = "Tomb";
        }
        return new HomeworldObject(HomeworldType,PlanetName,StarName,SystemType);
    }
    public String[] generatePortrait(){
        List <String> PortraitClasses = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.PortraitClasses)));
        if(has_plantoids) PortraitClasses.add("Plantoid");
        int random = new Random().nextInt(PortraitClasses.size());
        String PortraitClass = PortraitClasses.get(random);
        if(MachineEmpire) PortraitClass = "Synth";
        int[] portraits = {5,18,17,18,18,16,16,15,9};//Number of portraits in each species group{Humanoid,Mammalian,Reptilian,Avian,Arthropoid,Molluscoid,Fungoid,Plantoid,Synth}
        if(has_humanoids) portraits[0] = 15;
        switch(PortraitClass){
            case"Humanoid":random = new Random().nextInt(portraits[0]-1);
            case"Mammalian":random = new Random().nextInt(portraits[1]-1);
            case"Reptilian":random = new Random().nextInt(portraits[2]-1);
            case"Avian":random = new Random().nextInt(portraits[3]-1);
            case"Arthropoid":random = new Random().nextInt(portraits[4]-1);
            case"Molluscoid":random = new Random().nextInt(portraits[5]-1);
            case"Fungoid":random = new Random().nextInt(portraits[6]-1);
            case"Plantoid":random = new Random().nextInt(portraits[7]-1);
            case"Synth":random = new Random().nextInt(portraits[8]-1);
        }
        String[] Portrait = {PortraitClass,String.valueOf(random)};
        return Portrait;
    }
    public LayerDrawable createPortrait(String portrait){
        BitmapDrawable d1 = new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(),R.mipmap.spacebackground));
        BitmapDrawable d2 = null;
        try {
            Class res = R.mipmap.class;
            Field field = res.getField(portrait.toLowerCase());
            d2 = new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(),field.getInt(null)));
        }
        catch (Exception e) {
            Log.e(portrait.toLowerCase()+".png", "Failure to get drawable id.", e);
        }
        BitmapDrawable d3 = new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(),R.mipmap.backgroundframe));
        Drawable drawableArray[]= new Drawable[]{d1,d2,d3};
        LayerDrawable layerDraw = new LayerDrawable(drawableArray);
        return layerDraw;
    }
    public String generateEmpireName(String authority, CivicModel.CivicObject[] civics,String SpeciesName, String StarName, String HomeworldName){
        Gson gson = new Gson();
        String myJson = inputStreamToString(getResources().openRawResource(R.raw.empire_names));
        List<NameModel.NameObject> availibleNames = new ArrayList<>(Arrays.asList(gson.fromJson(myJson,NameModel.class).getNames()));
        for(int i =0; i<availibleNames.size();i++){
            if(!Arrays.asList(availibleNames.get(i).getRequiredAuthority()).contains(authority) && availibleNames.get(i).getRequiredAuthority().length>0){
                availibleNames.remove(i);
                i--;
            }
            else if(availibleNames.get(i).RequiredCivics.length > 0&&(!Arrays.asList(availibleNames.get(i).RequiredCivics).contains(civics[0].toString()))&&(!Arrays.asList(availibleNames.get(i).RequiredCivics).contains(civics[1].toString()))){
                availibleNames.remove(i);
                i--;
            }
        }
        int random = new Random().nextInt(availibleNames.size());
        String EmpireName = availibleNames.get(random).toString().replace("STAR_NAME",StarName).replace("SPECIES_NAME",SpeciesName).replace("HOMEWORLD_NAME",HomeworldName);
        return EmpireName;

    }
    public Drawable generateFlag(EmpireObject newEmpire){
        int bound = 149;
        int color1 = new Random().nextInt(20);
        int color2 = new Random().nextInt(20);
        if(color1 == color2 && color2 == 19) color2 = 18;
        else if(color1 == color2) color2 += 1;
        if(has_megacorp) bound +=6;
        String background = String.valueOf(new Random().nextInt(13));
        String icon = String.valueOf(new Random().nextInt(bound));
        if(Integer.parseInt(icon)>= 149){
            icon = "flag_icon_corporate_"+String.valueOf(new Random().nextInt(6));
        }
        else icon = "flag_icon_"+icon;
        ColorFilter BackgroundMain = new PorterDuffColorFilter(getResources().getColor(getResources().getIdentifier("color_"+String.valueOf(color1), "color", getPackageName())),PorterDuff.Mode.MULTIPLY);
        ColorFilter BackgroundSecondary = new PorterDuffColorFilter(getResources().getColor(getResources().getIdentifier("color_"+String.valueOf(color2), "color", getPackageName())),PorterDuff.Mode.MULTIPLY);
        BitmapDrawable d1 = new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(),R.mipmap.flag_background_main));
        BitmapDrawable d2 = new BitmapDrawable(getResources(),getDrawable("flag_background_"+background));
        BitmapDrawable d3 = new BitmapDrawable(getResources(),getDrawable(icon));
        d1.setColorFilter(BackgroundMain);
        d2.setColorFilter(BackgroundSecondary);
        Drawable drawableArray[]= new Drawable[]{d1,d2,d3};
        LayerDrawable layerDraw = new LayerDrawable(drawableArray);
        String[] flagArray = {String.valueOf(color1),String.valueOf(color2),background,icon};
        newEmpire.setFlag(flagArray);
        return layerDraw;
    }
    public Bitmap getDrawable(String drawableName){
        try {
            Class res = R.mipmap.class;
            Field field = res.getField(drawableName);
            return BitmapFactory.decodeResource(getResources(),field.getInt(null));
        }
        catch (Exception e) {
            Log.e(drawableName+".png", "Failure to get drawable id.", e);
        }
        return null;
    }
    public void ShowEmpire(){
        Context context = getApplicationContext();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        has_utopia = sharedPref.getBoolean("has_utopia",false);
        has_synthetic_dawn = sharedPref.getBoolean("has_synthetic_dawn",false);
        has_humanoids = sharedPref.getBoolean("has_humanoids",false);
        has_apocalypse = sharedPref.getBoolean("has_apocalypse",false);
        has_plantoids = sharedPref.getBoolean("has_plantoids",false);
        has_megacorp = sharedPref.getBoolean("has_megacorp",false);
        EmpireViewModel mViewModel = ViewModelProviders.of(this).get(EmpireViewModel.class);

        TextView EmpireName = findViewById(R.id.EmpireName);
        TextView Name = findViewById(R.id.Name);
        TextView Cityset = findViewById(R.id.Cityset);
        TextView Shipset = findViewById(R.id.Shipset);
        TextView Ethics = findViewById(R.id.Ethics);
        TextView Trait1 = findViewById(R.id.trait1);
        TextView Trait2 = findViewById(R.id.trait2);
        TextView Trait3 = findViewById(R.id.trait3);
        TextView Trait4 = findViewById(R.id.trait4);
        TextView Trait5 = findViewById(R.id.trait5);
        TextView Authority = findViewById(R.id.Authority);
        TextView Civic1 = findViewById(R.id.Civic1);
        TextView Civic2 = findViewById(R.id.Civic2);
        TextView HomeworldType = findViewById(R.id.HomeworldType);
        TextView HomeworldName = findViewById(R.id.HomeworldName);
        TextView HomeworldStar = findViewById(R.id.HomeworldStar);
        ImageView AuthImage = findViewById(R.id.authImg);
        ImageView Trait1Icon = findViewById(R.id.trait1Icon);
        ImageView Trait2Icon = findViewById(R.id.trait2Icon);
        ImageView Trait3Icon = findViewById(R.id.trait3Icon);
        ImageView Trait4Icon = findViewById(R.id.trait4Icon);
        ImageView Trait5Icon = findViewById(R.id.trait5Icon);
        ImageView Civic1Icon = findViewById(R.id.civic1Icon);
        ImageView Civic2Icon = findViewById(R.id.civic2Icon);
        ImageView Portrait = findViewById(R.id.portrait);
        ImageView Ethic1Icon = findViewById(R.id.EthicIcon1);
        ImageView Ethic2Icon = findViewById(R.id.EthicIcon2);
        ImageView Ethic3Icon = findViewById(R.id.EthicIcon3);
        ImageView PlanetIcon = findViewById(R.id.PlanetIcon);
        ImageView FlagView = findViewById(R.id.Flag);
        ImageButton SaveButton = findViewById(R.id.saveButton);
        ImageButton ReloadButton = findViewById(R.id.reloadButton);
        EmpireObject newEmpire = new EmpireObject();
        generateRandomEmpire(newEmpire);
        String[] EmpireAttributes = newEmpire.getEmpireAttributes();
        EmpireName.setText(newEmpire.toString());
        Name.setText(EmpireAttributes[0]);
        Cityset.setText("Cityset: "+EmpireAttributes[3]);
        Shipset.setText("Shipset: "+EmpireAttributes[5]);
        Ethics.setText(createMultiLineText(newEmpire.getEmpireEthics()));
        if(newEmpire.getEmpireEthics()[1] != null && newEmpire.getEmpireEthics()[0] == null)Ethics.setText(newEmpire.getEmpireEthics()[1]);
        String[] newTraits = newEmpire.getEmpireTraits();
        Trait1.setText(newTraits[0]);
        Trait2.setText(newTraits[1]);
        Trait3.setText(newTraits[2]);
        Trait4.setText(newTraits[3]);
        Trait5.setText(newTraits[4]);
        Authority.setText(newEmpire.getAuthority());
        String[] newCivics = newEmpire.getEmpireCivics();
        Civic1.setText(newCivics[0]);
        Civic2.setText(newCivics[1]);
        HomeworldObject Homeworld = newEmpire.getHomeworld();
        HomeworldType.setText(Homeworld.getType()+" World");
        HomeworldName.setText(Homeworld.getName());
        HomeworldStar.setText("Star: "+newEmpire.getHomeworld().getStar());
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence Savetext = "Empire Saved";
                mViewModel.insertEmpire(newEmpire);
                SaveButton.setClickable(false);
                Toast SaveMsg = Toast.makeText(context,Savetext,Toast.LENGTH_SHORT);
                SaveMsg.show();
            }
        });
        ReloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(context, DisplayCreatedEmpire.class));
                overridePendingTransition(0, 0);
            }
        });
        AuthImage.setImageBitmap(getDrawable("auth_"+newEmpire.getAuthority().toLowerCase().replaceAll(" ","_")));
        if(newTraits[0] != null) Trait1Icon.setImageBitmap(getDrawable("trait_" + newTraits[0].toLowerCase().replaceAll(" ","_")));
        if(newTraits[1] != null) Trait2Icon.setImageBitmap(getDrawable("trait_" + newTraits[1].toLowerCase().replaceAll(" ","_")));
        if(newTraits[2] != null) Trait3Icon.setImageBitmap(getDrawable("trait_" + newTraits[2].toLowerCase().replaceAll(" ","_")));
        if(newTraits[3] != null) Trait4Icon.setImageBitmap(getDrawable("trait_" + newTraits[3].toLowerCase().replaceAll(" ","_")));
        if(newTraits[4] != null) Trait5Icon.setImageBitmap(getDrawable("trait_" + newTraits[4].toLowerCase().replaceAll(" ","_")));
        Civic1Icon.setImageBitmap(getDrawable("civic_" + newCivics[0].toLowerCase().replaceAll(" ","_")));
        Civic2Icon.setImageBitmap(getDrawable("civic_" + newCivics[1].toLowerCase().replaceAll(" ","_")));
        if(newEmpire.getEmpireEthics()[0] != null) Ethic1Icon.setImageBitmap(getDrawable("ethic_" + newEmpire.getEmpireEthics()[0].toLowerCase().replaceAll(" ", "_")));
        if(newEmpire.getEmpireEthics()[1] != null) Ethic2Icon.setImageBitmap(getDrawable("ethic_" + newEmpire.getEmpireEthics()[1].toLowerCase().replaceAll(" ", "_")));
        if(newEmpire.getEmpireEthics()[2] != null) Ethic3Icon.setImageBitmap(getDrawable("ethic_" + newEmpire.getEmpireEthics()[2].toLowerCase().replaceAll(" ", "_")));
        PlanetIcon.setImageBitmap(getDrawable("planet_"+newEmpire.getHomeworld().getType().toLowerCase()));
        Portrait.setImageDrawable(createPortrait(newEmpire.getPortrait()[0]+newEmpire.getPortrait()[1]));
        FlagView.setImageDrawable(generateFlag(newEmpire));
    }
}
