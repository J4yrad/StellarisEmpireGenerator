package Stellaris.Empire.Generator;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import java.lang.reflect.Field;

public class ShowEmpireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_empire);
        String EmpireId = getIntent().getStringExtra("Id");
        EmpireViewModel mViewModel = ViewModelProviders.of(this).get(EmpireViewModel.class);
        mViewModel.getEmpire(EmpireId).observeForever(new Observer<EmpireObject>() {
            @Override
            public void onChanged(@Nullable EmpireObject empire) {
                DisplayEmpire(empire);
            }
        });

    }
    public void DisplayEmpire(EmpireObject currentEmpire){
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
        EmpireName.setText(currentEmpire.getEmpireName());
        Name.setText(currentEmpire.getName());
        Cityset.setText("Cityset: "+currentEmpire.getCityset());
        Shipset.setText("Shipset: "+currentEmpire.getShipset());
        Ethics.setText(createMultiLineText(currentEmpire.getEmpireEthics()));
        if(currentEmpire.getEmpireEthics()[1] != null && currentEmpire.getEmpireEthics()[0] == null)Ethics.setText(currentEmpire.getEmpireEthics()[1]);
        String[] newTraits = currentEmpire.getEmpireTraits();
        Trait1.setText(newTraits[0]);
        Trait2.setText(newTraits[1]);
        Trait3.setText(newTraits[2]);
        Trait4.setText(newTraits[3]);
        Trait5.setText(newTraits[4]);
        Authority.setText(currentEmpire.getAuthority());
        String[] newCivics = currentEmpire.getEmpireCivics();
        Civic1.setText(newCivics[0]);
        Civic2.setText(newCivics[1]);
        HomeworldObject Homeworld = currentEmpire.getHomeworld();
        HomeworldType.setText(Homeworld.getType()+" World");
        HomeworldName.setText(Homeworld.getName());
        HomeworldStar.append(currentEmpire.getHomeworld().getStar());
        AuthImage.setImageBitmap(getDrawable("auth_"+currentEmpire.getAuthority().toLowerCase().replaceAll(" ","_")));
        if(newTraits[0] != null) Trait1Icon.setImageBitmap(getDrawable("trait_" + newTraits[0].toLowerCase().replaceAll(" ","_")));
        if(newTraits[1] != null) Trait2Icon.setImageBitmap(getDrawable("trait_" + newTraits[1].toLowerCase().replaceAll(" ","_")));
        if(newTraits[2] != null) Trait3Icon.setImageBitmap(getDrawable("trait_" + newTraits[2].toLowerCase().replaceAll(" ","_")));
        if(newTraits[3] != null) Trait4Icon.setImageBitmap(getDrawable("trait_" + newTraits[3].toLowerCase().replaceAll(" ","_")));
        if(newTraits[4] != null) Trait5Icon.setImageBitmap(getDrawable("trait_" + newTraits[4].toLowerCase().replaceAll(" ","_")));
        Civic1Icon.setImageBitmap(getDrawable("civic_" + newCivics[0].toLowerCase().replaceAll(" ","_")));
        Civic2Icon.setImageBitmap(getDrawable("civic_" + newCivics[1].toLowerCase().replaceAll(" ","_")));
        if(currentEmpire.getEmpireEthics()[0] != null) Ethic1Icon.setImageBitmap(getDrawable("ethic_" + currentEmpire.getEmpireEthics()[0].toLowerCase().replaceAll(" ", "_")));
        if(currentEmpire.getEmpireEthics()[1] != null) Ethic2Icon.setImageBitmap(getDrawable("ethic_" + currentEmpire.getEmpireEthics()[1].toLowerCase().replaceAll(" ", "_")));
        if(currentEmpire.getEmpireEthics()[2] != null) Ethic3Icon.setImageBitmap(getDrawable("ethic_" + currentEmpire.getEmpireEthics()[2].toLowerCase().replaceAll(" ", "_")));
        PlanetIcon.setImageBitmap(getDrawable("planet_"+currentEmpire.getHomeworld().getType().toLowerCase()));
        Portrait.setImageDrawable(createPortrait(currentEmpire.getPortrait()[0]+currentEmpire.getPortrait()[1]));
        FlagView.setImageDrawable(drawFlag(currentEmpire.getFlag()));

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
    public String createMultiLineText(String[] text){
        int i = 0;
        String MultiLineText = new String();
        while(i<text.length && text[i] != null){
            MultiLineText+=text[i]+'\n';
            i++;
        }
        return MultiLineText;
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
    public Drawable drawFlag(String[] Flag){
        String color1 = Flag[0];
        String color2 = Flag[1];
        String background = Flag[2];
        String icon = Flag[3];
        ColorFilter BackgroundMain = new PorterDuffColorFilter(getResources().getColor(getResources().getIdentifier("color_"+color1, "color", getPackageName())),PorterDuff.Mode.MULTIPLY);
        ColorFilter BackgroundSecondary = new PorterDuffColorFilter(getResources().getColor(getResources().getIdentifier("color_"+color2, "color", getPackageName())),PorterDuff.Mode.MULTIPLY);
        BitmapDrawable d1 = new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(),R.mipmap.flag_background_main));
        BitmapDrawable d2 = new BitmapDrawable(getResources(),getDrawable("flag_background_"+background));
        BitmapDrawable d3 = new BitmapDrawable(getResources(),getDrawable(icon));
        d1.setColorFilter(BackgroundMain);
        d2.setColorFilter(BackgroundSecondary);
        Drawable drawableArray[]= new Drawable[]{d1,d2,d3};
        LayerDrawable layerDraw = new LayerDrawable(drawableArray);
        String[] flagArray = {String.valueOf(color1),String.valueOf(color2),background,icon};
        return layerDraw;
    }

}
