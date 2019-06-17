package com.softwaregiants.careertinder.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.models.JobOpeningModel;

import java.util.ArrayList;
import java.util.List;

public class JobOpeningsAdapter extends RecyclerView.Adapter<JobOpeningsAdapter.JobOpeningsViewHolder> {

    private List<JobOpeningModel> jobOpenings = new ArrayList<JobOpeningModel>();
    private OnItemClickListener onItemClickListener;
    public JobOpeningModel jobOpening;

    public JobOpeningsAdapter(List<JobOpeningModel> jobOpeningModelList){
        this.jobOpenings = jobOpeningModelList;
    }

    @NonNull
    @Override
    public JobOpeningsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_openings_list_item, parent, false);
        JobOpeningsViewHolder jobOpeningsViewHolder = new JobOpeningsViewHolder(itemView, onItemClickListener);
        return jobOpeningsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobOpeningsViewHolder holder, int position) {
        JobOpeningModel jobOpeningModel = jobOpenings.get(position);
        holder.jobOpening.setImageResource(R.drawable.baseline_assignment_ind_black_24);
        holder.jobTitle.setText(jobOpeningModel.getJobTitle());
        holder.placeOfWork.setText(jobOpeningModel.getPlaceOfWork());
        holder.preferredSkill.setText(jobOpeningModel.getSkill1());
    }

    @Override
    public int getItemCount() {
        return jobOpenings.size();
    }

    public static class JobOpeningsViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public ImageView jobOpening;
        public TextView jobTitle;
        public TextView placeOfWork;
        public TextView preferredSkill;
        public ImageButton editJobOpening;
        public ImageButton deleteJobOpening;

        public JobOpeningsViewHolder(View v, final OnItemClickListener listener) {
            super(v);
            jobOpening = (ImageView) v.findViewById(R.id.IVJobOpening);
            jobTitle = (TextView) v.findViewById(R.id.TVJobTitle);
            placeOfWork = (TextView) v.findViewById(R.id.TVPlaceOfWork);
            preferredSkill = (TextView) v.findViewById(R.id.TVPreferredSkill);
            editJobOpening = (ImageButton) v.findViewById(R.id.imageButtonEdit);
            deleteJobOpening = (ImageButton) v.findViewById(R.id.imageButtonDelete);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            listener.onItemClick(pos);
                        }
                    }
                }
            });

            editJobOpening.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            listener.onEditClick(pos);
                        }
                    }
                }
            });

            deleteJobOpening.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(pos);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }
}
