package br.com.hackathongdg.recycleplus.products;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.hackathongdg.recycleplus.ProductsRepo;
import br.com.hackathongdg.recycleplus.R;
import br.com.hackathongdg.recycleplus.model.Product;

public class ProductsFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public ProductsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<Product> products = ProductsRepo.getInstance().getProducts();

        View view = inflater.inflate(R.layout.fragment_products, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_products_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ProductsAdapter adapter = new ProductsAdapter(products);
        mRecyclerView.setAdapter(adapter);

        return view;
    }
}
