<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class MainController extends Controller
{
    //home
    public function home()
    {
        return view("home")->with([
            'title' => "Home",
        ]);
    }
}
