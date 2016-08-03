<html>
	<head></head>
	<body>
		<h1>Freemarker View Engine</h1>
		
		<h2>Restaurantes participantes:</h2>
		
		<ul>
			<#list restaurants as entry>
				<li>${entry.getName()}</li>
			</#list>
		</ul>
	</body>
</html>