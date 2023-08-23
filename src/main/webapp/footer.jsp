
                <footer>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <p><span id="year"></span> &copy; copyright All Right Reserved.</p>
                            </div>
                        </div>
                    </div>
                </footer>
                <script>
               		 function date() {
                	  var d = new Date();
                	  var n = d.getFullYear();
                	  document.getElementById("year").innerHTML = n;
                	}
               		date();
                </script>