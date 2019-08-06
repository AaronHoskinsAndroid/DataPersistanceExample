package examples.aaronhoskins.com.datapersistance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class CarsRecyclerViewAdapter extends RecyclerView.Adapter<CarsRecyclerViewAdapter.ViewHolder> {
    ArrayList<Cars> carList = new ArrayList<>();

    public CarsRecyclerViewAdapter(ArrayList<Cars> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cars carBeingRendered = carList.get(position);
        holder.tvMake.setText(carBeingRendered.getMake());
        holder.tvModel.setText(carBeingRendered.getModel());
        holder.setVin(carBeingRendered.getVinNumber());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMake;
        TextView tvModel;
        String vin;
        public ViewHolder(final View itemView) {
            super(itemView);
            tvMake = itemView.findViewById(R.id.tvMake);
            tvModel = itemView.findViewById(R.id.tvModel);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CarDatabaseHelper helper = new CarDatabaseHelper(view.getContext());
                    helper.deleteCar(vin);
                    EventBus.getDefault().post(new MessageEvent(vin + " was Deleted"));
                    carList = helper.getAllCars();
                    notifyDataSetChanged();
                }
            });
        }

        public void setVin(String vin) {
            this.vin = vin;
        }
    }
}
