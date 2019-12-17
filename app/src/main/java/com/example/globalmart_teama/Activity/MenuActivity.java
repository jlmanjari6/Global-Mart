package com.example.globalmart_teama.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.globalmart_teama.AboutUsFragment;
import com.example.globalmart_teama.BrowseByStoresHomeFragment;
import com.example.globalmart_teama.HomeFragment;
import com.example.globalmart_teama.MyCartFragment;
import com.example.globalmart_teama.NeedHelpFragment;
import com.example.globalmart_teama.ProductDetailsFragment;
import com.example.globalmart_teama.PurchaseHistoryFragment;
import com.example.globalmart_teama.R;
import com.example.globalmart_teama.RecommendationsFragment;
import com.example.globalmart_teama.SearchItemFragment;
import com.example.globalmart_teama.ShopByCategoryHomeFragment;
import com.example.globalmart_teama.models.Database;
import com.example.globalmart_teama.models.ProductsModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;


public class MenuActivity extends AppCompatActivity {

    //intialising variable for Drawer layout, navigation view and bar
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle togglelayout;
    private NavigationView navigationView;
    private FragmentTransaction fragmentTransaction;
    private BottomNavigationView bottomView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //fetching the value of drawer, toggle layout
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_header_id);
        togglelayout = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(togglelayout);
        togglelayout.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initiating fragment manager
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //replacing the fragment view with the home fragment view
        fragmentTransaction.replace(R.id.main_container, new HomeFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home");

        //fetching the value of bottom navigation bar
        bottomView = findViewById(R.id.bottom_nav);
        navigationView = findViewById(R.id.navigationView_id);

        //listening to the top navigation drawer input
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //performing action based on menu item id

                switch (menuItem.getItemId()) {

                    //if my cart is selected in menu items redirecting it to My cart fragment
                    case R.id.my_cart_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new MyCartFragment(), "MYCART");
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("My Cart");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    //if about us is selected in menu items redirecting it to About Us fragment
                    case R.id.about_us_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new AboutUsFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("About Us");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    //if Need Help is selected in menu items redirecting it to Need help fragment
                    case R.id.need_help_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new NeedHelpFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Help & Support");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    //if Recommendation is selected in menu items redirecting it to Recommendation fragment
                    case R.id.feedback_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new RecommendationsFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Recommendations");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    //if Purchase history is selected in menu items redirecting it to Purchase history fragment
                    case R.id.purchase_history_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new PurchaseHistoryFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Purchase History");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "default case", Toast.LENGTH_SHORT);

                }

                return true;
            }
        });

        //listening to the bottom navigation drawer input
        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    //if Home is selected in menu items redirecting it to Home fragment
                    case R.id.home:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new HomeFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Home");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    //if Search is selected in menu items redirecting it to Search fragment
                    case R.id.search:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new SearchItemFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Search bar");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    //if STORES is selected in menu items redirecting it to Stores fragment
                    case R.id.store_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new BrowseByStoresHomeFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Stores");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    //if Categories is selected in menu items redirecting it to Categories fragment
                    case R.id.categories:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new ShopByCategoryHomeFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Categories");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (togglelayout.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();

            final Database database = new Database(this);
            List<ProductsModel> df = null;
            df = database.getProductsModels();

            ProductsModel currProduct = null;

            for (ProductsModel name : df) {
                if ((name.getProductCode().toLowerCase()).equals(scanContent)) {
                    currProduct = name;

                }
            }

            Bundle detailsBundle = new Bundle();
            detailsBundle.putString("productName", currProduct.getProductName());
            detailsBundle.putString("productDesc", currProduct.getProductDesc());
            detailsBundle.putInt("productPrice", currProduct.getProductPrice());
            detailsBundle.putString("productImageId", currProduct.getProductImageID());

            ProductDetailsFragment fragment = new ProductDetailsFragment();
            fragment.setArguments(detailsBundle);

            FragmentTransaction ft = fragmentTransaction = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_container, fragment, "PRODUCT DESCRIPTION");
            ft.addToBackStack(null);
            ft.commit();
            getSupportActionBar().setTitle("Product Details");

        } else {
            Toast toast = Toast.makeText(this.getApplicationContext(),
                    "Product not found!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}
