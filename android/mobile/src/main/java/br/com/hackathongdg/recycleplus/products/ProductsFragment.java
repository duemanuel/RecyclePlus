package br.com.hackathongdg.recycleplus.products;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.hackathongdg.recycleplus.ProductsRepo;
import br.com.hackathongdg.recycleplus.R;
import br.com.hackathongdg.recycleplus.model.Product;

public class ProductsFragment extends Fragment {

    public ProductsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        List<Product> products = ProductsRepo.getInstance().getProducts();

        return view;
    }
}
