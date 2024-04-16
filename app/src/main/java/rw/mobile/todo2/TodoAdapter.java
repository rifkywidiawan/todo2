package rw.mobile.todo2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private List<TodoItem> todoItems = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TodoItem todoItem = todoItems.get(position);
        holder.bind(todoItem);
    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }

    public void setData(List<TodoItem> newTodoItems) {
        todoItems = newTodoItems;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView idTextView;
        private TextView whatTextView;
        private TextView timeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.id_text_view);
            whatTextView = itemView.findViewById(R.id.what_text_view);
            timeTextView = itemView.findViewById(R.id.time_text_view);
        }

        public void bind(TodoItem todoItem) {
            idTextView.setText(String.valueOf(todoItem.getId()));
            whatTextView.setText(todoItem.getWhat());
            timeTextView.setText(todoItem.getTime());
        }
    }
}
