package com.surajdevgan.recyclerviewkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

     lateinit var userRV: RecyclerView
     lateinit var userModalArrayList: ArrayList<UserModel>
    lateinit var tempArrayList: ArrayList<UserModel>
     lateinit var userRVAdapter: UserAdapter
     val BASE_URL = "https://reqres.in/api/users"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userRV = findViewById(R.id.rcView);
        userRV.layoutManager = LinearLayoutManager(this)
        //  userRV.setHasFixedSize(true)

         userModalArrayList = arrayListOf<UserModel>()
        tempArrayList = ArrayList()
        userRVAdapter = UserAdapter(tempArrayList, this)


// on below line we are initializing our list
      //  userModalArrayList = ArrayList()



// on below line we are initializing our adapter.
       // userRVAdapter = UserAdapter(userModalArrayList)

// on below line we are setting
// adapter to recycler view.


        getDataFromAPI()




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_item, menu)
        val item = menu?.findItem(R.id.search_action)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                tempArrayList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if(searchText.isNotEmpty())
                {

                    userModalArrayList.forEach{

                        if(it.first_name!!.lowercase(Locale.getDefault()).contains(searchText) || it.last_name!!.lowercase(Locale.getDefault()).contains(searchText))
                            {

                            tempArrayList.add(it)
                            }
                    }



                    userRVAdapter.notifyDataSetChanged()

                    userRV.adapter!!.notifyDataSetChanged()

                }

                else{

                    tempArrayList.clear()
                    tempArrayList.addAll(userModalArrayList)
                    userRVAdapter.notifyDataSetChanged()

                    userRV.adapter!!.notifyDataSetChanged()

                }


                return false
            }


        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun getDataFromAPI() {


// on below line we are creating a
// variable for our request queue
        val queue = Volley.newRequestQueue(this)


// on below line we are creating a request
// variable for making our json object request.
        val request = StringRequest(Request.Method.GET, BASE_URL,
            { response ->
// this method is called when we get successful response from API.
                try {
                    val `object` = JSONObject(response)
                    val jsonArray = `object`.getJSONArray("data")

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val id = jsonObject.getInt("id")
                        val Email = jsonObject.getString("email")
                        val ImageUrl = jsonObject.getString("avatar")
                        val FirstName = jsonObject.getString("first_name")
                        val LastName = jsonObject.getString("last_name")

                        userModalArrayList.add(UserModel(id, Email, FirstName, LastName, ImageUrl))

                    }

                    tempArrayList.addAll(userModalArrayList)


                    userRVAdapter.notifyDataSetChanged()

                    userRV.adapter = userRVAdapter



                    userRVAdapter.notifyDataSetChanged()



                    userRVAdapter.onItemClick = {

                     val intent = Intent(this, DetailsActivity::class.java)
                        intent.putExtra("currentUserData", it)
                        startActivity(intent)
                    }


                    userRVAdapter.onImageClick = {

                        Toast.makeText(this,"ImageClick",Toast.LENGTH_SHORT).show()
                    }




// on below line we
// are handling exception
                } catch (e: Exception) {
                    Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()

                }

            }, {

                Toast.makeText(this, "Fail to get response", Toast.LENGTH_SHORT).show()

            })
        queue.add(request)
    }

}