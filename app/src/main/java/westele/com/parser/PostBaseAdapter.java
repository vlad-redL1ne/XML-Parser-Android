package westele.com.parser;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PostBaseAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<PostValue> postValueArrayList;

    public PostBaseAdapter(Context context, ArrayList<PostValue> postValueArrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.postValueArrayList = postValueArrayList;
    }

    @Override
    public int getCount() {
        return postValueArrayList.size();
    }

    @Override
    public PostValue getItem(int position) {
        return postValueArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.list_item_post, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PostValue postValue = getItem(position);
        viewHolder.id.setText(postValue.getId());
        viewHolder.slug.setText(postValue.getSlug());
        viewHolder.img.setText(postValue.getImg());

        return convertView;
    }

    private class ViewHolder {
        TextView id, slug, img;

        public ViewHolder(View item) {
            id = (TextView) item.findViewById(R.id.id);
            slug = (TextView) item.findViewById(R.id.slug);
            img = (TextView) item.findViewById(R.id.img);
        }
    }
}