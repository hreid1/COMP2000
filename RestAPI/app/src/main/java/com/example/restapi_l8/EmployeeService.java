package com.example.restapi_l8;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;


public class EmployeeService {

    private static final String BASE_URL = "http://10.224.41.11/comp2000";
    private static RequestQueue requestQueue;
    private static final Gson gson = new Gson();

    // Initialize RequestQueue if needed
    private static void initQueue(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
    }

    // Method to get all employees
    public static void getAllEmployees(Context context) {
        initQueue(context);
        String url = BASE_URL + "/employees";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Type listType = new TypeToken<List<Employee>>() {}.getType();
                        List<Employee> employees = gson.fromJson(response.toString(), listType);

                        // onSuccess behavior - Log each employee's firstname and salary
                        for (Employee employee : employees) {
                            Log.d("EmployeeInfo", "Firstname: " + employee.getFirstname() + ", Salary: " + employee.getSalary());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // onError behavior - Log the error
                        Log.e("EmployeeError", "Error retrieving employees: " + error.getMessage());
                    }
                }
        );

        requestQueue.add(request);
    }

    // Method to get an employee by ID
    public static void getEmployeeById(Context context, int id) {
        initQueue(context);
        String url = BASE_URL + "/employees/get/" + id;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Employee employee = gson.fromJson(response.toString(), Employee.class);

                        // onSuccess behavior - Log employee details
                        Log.d("EmployeeInfo", "Firstname: " + employee.getFirstname() + ", Salary: " + employee.getSalary());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // onError behavior - Log the error
                        Log.e("EmployeeError", "Error retrieving employee by ID: " + error.getMessage());
                    }
                }
        );

        requestQueue.add(request);
    }

    // Method to add a new employee
    public static void addEmployee(Context context, Employee employee) {
        initQueue(context);
        String url = BASE_URL + "/employees/add";

        try {
            JSONObject jsonRequest = new JSONObject(gson.toJson(employee));

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // onSuccess behavior - Log the success message from the API response
                            String message = response.optString("message", "Employee added successfully");
                            Log.d("EmployeeService", message);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // onError behavior - Log the error
                            Log.e("EmployeeError", "Error adding employee: " + error.getMessage());
                        }
                    }
            );

            requestQueue.add(request);
        } catch (JSONException e) {
            Log.e("EmployeeError", "Invalid JSON format: " + e.getMessage());
        }
    }

    // Method to update an existing employee
    public static void updateEmployee(Context context, int id, Employee employee) {
        initQueue(context);
        String url = BASE_URL + "/employees/edit/" + id;

        try {
            JSONObject jsonRequest = new JSONObject(gson.toJson(employee));

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonRequest,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // onSuccess behavior - Log the success message from the API response
                            String message = response.optString("message", "Employee updated successfully");
                            Log.d("EmployeeService", message);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // onError behavior - Log the error
                            String errorMessage = "Unknown error";

                            if (error.networkResponse != null) {
                                errorMessage = "Status Code: " + error.networkResponse.statusCode;
                                String responseBody = new String(error.networkResponse.data);
                                Log.e("EmployeeError", "Error Body: " + responseBody);
                            }

                            Log.e("EmployeeError", "Error updating employee: " + errorMessage);
                        }
                    }
            );

            requestQueue.add(request);
        } catch (JSONException e) {
            Log.e("EmployeeError", "Invalid JSON format: " + e.getMessage());
        }
    }

    // Method to delete an employee
    public static void deleteEmployee(Context context, int id) {
        initQueue(context);
        String url = BASE_URL + "/employees/delete/" + id;

        StringRequest request = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // onSuccess behavior - Log success message
                        Log.d("EmployeeService", "Employee deleted successfully");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // onError behavior - Log the error
                        Log.e("EmployeeError", "Error deleting employee: " + error.getMessage());
                    }
                }
        );

        requestQueue.add(request);
    }
}
