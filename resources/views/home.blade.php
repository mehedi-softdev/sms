@if (session()->has("username"))
@extends("layout.app")
@section("container")
    <x-primary_menu />
   <h1>I am found</h1>

@endsection
@else
    <script>
        window.location.href = "/login";
    </script>
@endif
