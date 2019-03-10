package stellaris.empire.generator;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.lang.reflect.Field;
import java.util.List;

public class EmpiresAdapter extends RecyclerView.Adapter<EmpiresAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public ImageView EmpirePortrait;
        public ImageView FlagView;
        public ImageButton deleteButton;
        public Button ViewButton;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            EmpirePortrait= (ImageView) itemView.findViewById(R.id.EmpirePortrait);
            nameTextView = (TextView) itemView.findViewById(R.id.EmpireName);
            deleteButton = (ImageButton) itemView.findViewById(R.id.EmpireDelete);
            FlagView = (ImageView) itemView.findViewById(R.id.EmpireFlag);
            ViewButton = (Button) itemView.findViewById(R.id.viewButton);
        }
    }
    private List<EmpireObject> EmpireList;
    private EmpireViewModel EmpireModel;
    public EmpiresAdapter(List<EmpireObject> empires, EmpireViewModel eModel) {

        EmpireList = empires;
        EmpireModel = eModel;
    }
    @Override
    public EmpiresAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.content_view_all_empires, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(EmpiresAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        EmpireObject listEmpire = EmpireList.get(position);

        // Set item views based on your views and data model
        Resources resources = viewHolder.itemView.getContext().getResources();
        TextView nameTextView = viewHolder.nameTextView;
        ImageView EmpirePortrait = viewHolder.EmpirePortrait;
        ImageView EmpireFlag = viewHolder.FlagView;
        ImageButton DeleteButton = viewHolder.deleteButton;
        Button ViewButton = viewHolder.ViewButton;
        nameTextView.setText(listEmpire.getEmpireName());
        BitmapDrawable d1 = new BitmapDrawable(viewHolder.itemView.getResources(),BitmapFactory.decodeResource(viewHolder.itemView.getResources(),R.mipmap.spacebackground));
        BitmapDrawable d2 = null;
        try {
            Class res = R.mipmap.class;
            Field field = res.getField(listEmpire.getPortrait()[0].toLowerCase()+listEmpire.getPortrait()[1]);
            d2 = new BitmapDrawable(resources,BitmapFactory.decodeResource(resources,field.getInt(null)));
        }
        catch (Exception e) {
            Log.e(listEmpire.getPortrait()[0].toLowerCase()+listEmpire.getPortrait()[1]+".png", "Failure to get drawable id.", e);
        }
        BitmapDrawable d3 = new BitmapDrawable(resources,BitmapFactory.decodeResource(viewHolder.itemView.getResources(),R.mipmap.backgroundframe));
        Drawable drawableArray[]= new Drawable[]{d1,d2,d3};
        LayerDrawable layerDraw = new LayerDrawable(drawableArray);
        EmpirePortrait.setImageDrawable(layerDraw);
        String[] Flag = listEmpire.getFlag();
        ColorFilter BackgroundMain = new PorterDuffColorFilter(resources.getColor(resources.getIdentifier("color_"+Flag[0], "color", viewHolder.itemView.getContext().getPackageName())),PorterDuff.Mode.MULTIPLY);
        ColorFilter BackgroundSecondary = new PorterDuffColorFilter(resources.getColor(resources.getIdentifier("color_"+Flag[1], "color", viewHolder.itemView.getContext().getPackageName())),PorterDuff.Mode.MULTIPLY);
        BitmapDrawable Background1 = new BitmapDrawable(viewHolder.itemView.getResources(),BitmapFactory.decodeResource(viewHolder.itemView.getResources(),R.mipmap.flag_background_main));
        BitmapDrawable Background2 = null;
        try {
            Class res = R.mipmap.class;
            Field field = res.getField("flag_background_"+Flag[2]);
            Background2 = new BitmapDrawable(viewHolder.itemView.getResources(),BitmapFactory.decodeResource(viewHolder.itemView.getResources(),field.getInt(null)));
        }
        catch (Exception e) {
            Log.e("flag_background_"+Flag[2]+".png", "Failure to get drawable id.", e);
        }
        BitmapDrawable FlagIcon = null;
        try {
            Class res = R.mipmap.class;
            Field field = res.getField(Flag[3]);
            FlagIcon = new BitmapDrawable(viewHolder.itemView.getResources(),BitmapFactory.decodeResource(viewHolder.itemView.getResources(),field.getInt(null)));
        }
        catch (Exception e) {
            Log.e("flag_background_"+Flag[3]+".png", "Failure to get drawable id.", e);
        }
        Background1.setColorFilter(BackgroundMain);
        Background2.setColorFilter(BackgroundSecondary);
        Drawable flagDrawable[]= new Drawable[]{Background1,Background2,FlagIcon};
        LayerDrawable flagDraw = new LayerDrawable(flagDrawable);
        EmpireFlag.setImageDrawable(flagDraw);
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmpireModel.removeEmpire(listEmpire);
            }
        });
        ViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewHolder.itemView.getContext(), ShowEmpireActivity.class);
                String EmpireId = listEmpire.getId().toString();
                intent.putExtra("Id",EmpireId);
                viewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return EmpireList.size();
    }
    public void setEmpireList(List<EmpireObject> newList){
        this.EmpireList=newList;
    }

}
