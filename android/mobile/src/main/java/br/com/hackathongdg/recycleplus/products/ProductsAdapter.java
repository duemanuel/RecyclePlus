package br.com.hackathongdg.recycleplus.products;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.hackathongdg.recycleplus.R;
import br.com.hackathongdg.recycleplus.model.Product;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImageView;
        public TextView mTextView;

        public ViewHolder(ViewGroup v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.info_text);
            mImageView = (ImageView) v.findViewById(R.id.card_image);
        }
    }

    private List<Product> mDataset;

    public ProductsAdapter(List<Product> dataset) {
        mDataset = dataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_products, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = mDataset.get(position);
        Context context = holder.mImageView.getContext();

        Picasso.with(context).load(product.getPhoto()).into(holder.mImageView);
        holder.mTextView.setText(product.getTitle());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}