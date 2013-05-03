<%@include file="header.jsp" %>

<h1>${movie.name}, ${movie.year}</h1>

<img src="${movie.poster}" />

<div>Režie: ${movie.directors}</div>
<div>Scénář: ${movie.script}</div>
<div>Herci: ${movie.actors}</div>
<div>Země původu: ${movie.countries}</div>
<div>Žánry: ${movie.genres}</div>
<div>Délka: ${movie.length}</div>

<div>${movie.description}</div>

<div>${movie.rating}%</div>
<div>${movie.price}</div>

<%@include file="footer.jsp" %>