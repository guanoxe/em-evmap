package com.koroc.evmap

import com.koroc.evmap.Model._

/**
	* A hardcoded set of data
	*  - operators
	*  - chargePoints
	*  - campings
	*  - locations = chargePoints + campings
	*/
object StaticData {
	// constants
	val going_south = "going south"
	val going_north = "going north"
	val going_east = "going east"
	val going_west = "going west"
	val all = "all"

	val pay_fastned = "Fastned app"
	val pay_newmotion = "Newmotion card"
	val pay_sodetrel = "Sodetrel card"

	val src_newmotion = "newmotion.com"
	val src_fastned = "fastned.nl"
	val src_sodetrel = "sodetrel.fr"
	val src_sander = "Sander"

	val status_active = "active"
	val status_closed = "closed"

	val out_of_service = "out of service"

	// operators
	val fastned = Operator(
		id = "evmap:fastned",
		name = "Fastned",
		icon = Icon("fastned","svg")
	)
	val allego = Operator(
		name = "Allego",
		icon = Icon("allego","svg")
	)
	val corri_door = Operator(
		name = "GIREVE Corri-Door",
		icon = Icon("corri-door","svg"),
		iconFail = Icon("corri-door-red","svg")
	)
	val operators = List(fastned, allego)


	// chargepoints
	val chargePoints = List(
		//========== Belgie ==========
		ChargePoint(
			name = "Total Vaux-Chavanne",
			location = GeoPoint(lat = 50.30649, lon = 5.71292, country = "BE", city = "Vaux-Chavanne", address = "Rue des Boussines 35 DODO Total Vaux-Chavanne, 6960, Vaux-Chavanne"),
			osm = List("way/103037598"),
			operator = allego,
			payWith = List(pay_newmotion),
			sources = List(src_newmotion)
		),
		ChargePoint(
			name = "Carrefour Luik",
			location = GeoPoint(lat = 50.6533, lon = 5.52852, country = "BE", city = "Luik", address = "Rue Jean Jaures 33 Near Carrefour, 4430, Ans"),
			operator = allego,
			payWith = List(pay_newmotion),
			sources = List(src_newmotion)
		),
		ChargePoint(
			name = "Avenue de Luxembourg 1 TOTAL Jambes",
			location = GeoPoint(lat = 50.4636, 4.8877, country = "BE", city = "Namen"),
			operator = allego,
			payWith = List(pay_newmotion),
			sources = List(src_newmotion)
		),
		ChargePoint(
			name = "Boulevard de l'Europe 3 near Carrefour",
			location = GeoPoint(lat = 50.71156, 4.60467, country = "BE", city = "Namen"),
			operator = allego,
			payWith = List(pay_newmotion),
			sources = List(src_newmotion)
		),
		ChargePoint(
			name = "Grensovergang Lichtenbusch A44 Noord",
			location = GeoPoint(lat = 50.71819, 6.11829, country = "BE"),
			roadName = "A44",
			operator = allego,
			payWith = List(pay_newmotion),
			sources = List(src_newmotion)
		),
		ChargePoint(
			name = "Grensovergang Lichtenbusch A44 Zuid",
			location = GeoPoint(lat = 50.71713, 6.11894, country = "BE"),
			roadName = "A44",
			operator = allego,
			payWith = List(pay_newmotion),
			sources = List(src_newmotion)
		),
		//========== France ==========
		ChargePoint(
			name = "Intermarche Metz",
			location = GeoPoint(lat = 49.12064, lon = 6.22015, country = "FR", city = "Metz"),
			accessible = all,
			operator = corri_door,
			payWith = List(pay_newmotion, pay_sodetrel),
			sources = List(src_newmotion, src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Service de Toul-Chaudeney",
			location = GeoPoint(lat = 48.660759, lon = 5.907556, country = "FR"),
			roadName = "A31",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_newmotion, pay_sodetrel),
			osm = List("way/141404199"),
			sources = List(src_newmotion)
		),
		ChargePoint(
			name = "Aire de Keskastel OUEST",
			location = GeoPoint(lat = 48.9745, lon = 7.06486, country = "FR"),
			roadName = "A4",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			osm = List("way/210441272"),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Keskastel EST",
			location = GeoPoint(lat = 48.97259, lon = 7.06864, country = "FR"),
			roadName = "A4",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			osm = List("way/210441270"),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Brumath OUEST",
			location = GeoPoint(lat = 48.72467, lon = 7.69125, country = "FR"),
			roadName = "A4",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			osm = List("way/495962417"),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Brumath EST",
			location = GeoPoint(lat = 48.721, lon = 7.69638, country = "FR"),
			roadName = "A4",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			osm = List("way/310943730"),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Valmy le Moulin NORTH",
			location = GeoPoint(lat = 49.07393, lon = 4.79118, country = "FR"),
			roadName = "A4",
			accessible = going_west,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Valmy le Moulin SOUTH",
			location = GeoPoint(lat = 49.07209, lon = 4.78834, country = "FR"),
			roadName = "A4",
			accessible = going_east,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel),
			status = "bientot disponible"
		),
		ChargePoint(
			name = "Aire de Lorraine Sandaucourt la Trelle",
			location = GeoPoint(lat = 48.26891, lon = 5.86037, country = "FR"),
			roadName = "A31",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Lorraine Sandaucourt les Rappes",
			location = GeoPoint(lat = 48.26753, lon = 5.8633, country = "FR"),
			roadName = "A31",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Montigny le Roi",
			location = GeoPoint(lat = 47.97733, lon = 5.4976, country = "FR"),
			roadName = "A31",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Val de Meuse",
			location = GeoPoint(lat = 47.97575, lon = 5.49903, country = "FR"),
			roadName = "A31",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Langres Perrogney",
			location = GeoPoint(lat = 47.8155, lon = 5.22218, country = "FR"),
			roadName = "A31",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel),
			status = out_of_service
		),
		ChargePoint(
			name = "Aire de Langres Noidant",
			location = GeoPoint(lat = 47.81452, lon = 5.2257, country = "FR"),
			roadName = "A31",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Dijon Brognon (OUEST)",
			location = GeoPoint(lat = 47.42285, lon = 5.17039, country = "FR"),
			roadName = "A31",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Dijon Brognon (EST)",
			location = GeoPoint(47.42288, 5.16868, country = "FR"),
			roadName = "A31",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Beaune-Tailly",
			location = GeoPoint(46.9641, 4.83599, country = "FR"),
			roadName = "A6",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Beaune-Merceuil",
			location = GeoPoint(46.96346, 4.8382, country = "FR"),
			roadName = "A6",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Mâcon Saint Albain",
			location = GeoPoint(46.41982, 4.86438, country = "FR"),
			roadName = "A6",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Mâcon La Salle",
			location = GeoPoint(46.41789, 4.86506, country = "FR"),
			roadName = "A6",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Dracé",
			location = GeoPoint(46.14383, 4.76772, country = "FR"),
			roadName = "A6",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel),
			status = "unknown"
		),
		ChargePoint(
			name = "Aire de Taponas",
			location = GeoPoint(46.1361, 4.76681, country = "FR"),
			roadName = "A6",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Glanon",
			location = GeoPoint(47.03194, 5.10832, country = "FR"),
			roadName = "A36",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire du Bois Guillerot",
			location = GeoPoint(47.03045, 5.10851, country = "FR"),
			roadName = "A36",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel),
			status = "unknown"
		),
		ChargePoint(
			name = "Aire de Besançon Champoux",
			location = GeoPoint(47.32966, 6.12725, country = "FR"),
			roadName = "A36",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Besançon Marchaux",
			location = GeoPoint(47.32583, 6.1222, country = "FR"),
			roadName = "A36",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "E.Leclerc Belfort",
			location = GeoPoint(47.62768, 6.86053, country = "FR"),
			roadName = "A36",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Intermarché Pfastatt",
			location = GeoPoint(47.75954, 7.29355, country = "FR"),
			roadName = "A36",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire du Haut Koenigsbourg",
			location = GeoPoint(48.23177, 7.40345, country = "FR"),
			roadName = "A35",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire du Poulet de Bresse",
			location = GeoPoint(46.49598, 5.3107, country = "FR"),
			roadName = "A39",
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel),
			status = "unknown"
		),
		ChargePoint(
			name = "Réseau Move In Pure - Bellegarde-sur-Valserine",
			location = GeoPoint(46.10878, 5.82985, country = "FR"),
			accessible = all,
			operator = corri_door,
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Réseau Move In Pure - Seyssel",
			location = GeoPoint(45.95685, 5.83437, country = "FR"),
			accessible = all,
			operator = corri_door,
			osm = List("node/4351325779"),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Station Haudainville",
			location = GeoPoint(49.14069, 5.40984, country = "FR"),
			accessible = all,
			operator = corri_door,
			osm = List(),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Reims Champagne Nord",
			location = GeoPoint(49.12097, 4.24421, country = "FR"),
			accessible = all,
			roadName = "A4",
			operator = corri_door,
			osm = List(),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Reims Champagne Sud",
			location = GeoPoint(49.11983, 4.24342, country = "FR"),
			accessible = all,
			roadName = "A4",
			operator = corri_door,
			osm = List(),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "E.Leclerc Champfleury",
			location = GeoPoint(49.20259, 4.01357, country = "FR"),
			accessible = all,
			operator = corri_door,
			osm = List(),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "AIRE DES ARDENNES",
			location = GeoPoint(49.59331, 4.50408, country = "FR"),
			accessible = all,
			roadName = "A34",
			operator = corri_door,
			osm = List(),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel),
			status = status_closed,
			description = "niet gezien op streetview"
		),
		ChargePoint(
			name = "Intermarché Laon",
			location = GeoPoint(49.58287, 3.64379, country = "FR"),
			accessible = all,
			operator = corri_door,
			osm = List(),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel),
			status = "unknown",
			description = "nissan lader gezien op streetview, maar status onduidelijk"
		),
		ChargePoint(
			name = "Aire de Sommesous",
			location = GeoPoint(48.72964, 4.22912, country = "FR"),
			accessible = all,
			roadName = "A26",
			operator = corri_door,
			osm = List(),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Troyes Fresnoy le Plessis",
			location = GeoPoint(48.2141, 4.2416, country = "FR"),
			accessible = going_east,
			roadName = "A5",
			operator = corri_door,
			osm = List(),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel),
			status = "unknown"
		),
		ChargePoint(
			name = "Aire de Troyes Fresnoy le Château",
			location = GeoPoint(48.2117, 4.2415, country = "FR"),
			accessible = going_west,
			roadName = "A5",
			operator = corri_door,
			osm = List(),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Chateauvillain EST",
			location = GeoPoint(48.0565, 4.96102, country = "FR"),
			accessible = all,
			roadName = "A5",
			operator = corri_door,
			osm = List(),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Aire de Chateauvillain OUEST",
			location = GeoPoint(48.05587, 4.96071, country = "FR"),
			accessible = all,
			roadName = "A5",
			operator = corri_door,
			osm = List(),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel),
			description = "verkeerde kant van de weg op Sodetrel kaart?"
		),
		ChargePoint(
			name = "Réseau Move In Pure | Avenue Daniel Rops Aix-les-Bains",
			location = GeoPoint(45.69098, 5.89539, country = "FR"),
			accessible = all,
			operator = corri_door,
			osm = List(),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Réseau Move In Pure | D107",
			location = GeoPoint(45.75886, 5.70588, country = "FR"),
			accessible = all,
			operator = corri_door,
			osm = List(),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Réseau Move In Pure | Rue de Pont",
			location = GeoPoint(45.85325, 5.40702, country = "FR"),
			accessible = all,
			operator = corri_door,
			osm = List(),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		ChargePoint(
			name = "Réseau Move In Pure | Rue Charles de Gaulle",
			location = GeoPoint(45.83737, 5.28612, country = "FR"),
			accessible = all,
			operator = corri_door,
			osm = List(),
			payWith = List(pay_sodetrel),
			sources = List(src_sodetrel)
		),
		//========== Fastned ==========
		ChargePoint(
			name = "Fastned Bijleveld",
			location = GeoPoint(lat = 52.07939, lon = 4.9813, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("node/387321930"),
			roadName = "A12", accessible = going_east,
			sources = List(src_fastned, src_sander)
		),
		ChargePoint(
			name = "Fastned Ooiendonk",
			location = GeoPoint(lat = 51.5567735, lon = 5.3620474, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("node/4383277848"),
			roadName = "A2", accessible = going_north,
			sources = List(src_fastned)
		),
		ChargePoint(
			name = "Fastned Velder",
			location = GeoPoint(lat = 51.5591013, lon = 5.3565562, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("node/4383277847"),
			roadName = "A2", accessible = going_south,
			sources = List(src_fastned)
		),
		ChargePoint(
			name = "Fastned Ellerbrug",
			location = GeoPoint(lat = 51.2205685, lon = 5.8091146, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("way/388650592", "node/3918256186"),
			roadName = "A2", accessible = going_south,
			sources = List(src_fastned)
		),
		ChargePoint(
			name = "Fastned Swentibold",
			location = GeoPoint(lat = 51.0108706, lon = 5.8004800, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("node/3906768630"),
			roadName = "A2", accessible = going_north,
			sources = List(src_fastned)
		),
		ChargePoint(
			name = "Fastned Knuvelkes",
			location = GeoPoint(lat = 50.7860308, lon = 5.7277984, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("node/3264836068"),
			roadName = "A2", accessible = going_south,
			sources = List(src_fastned)
		),

		ChargePoint(
			name = "Fastned de Geffense Barriere",
			location = GeoPoint(51.73455, 5.49283, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("way/387364205"),
			roadName = "A59", accessible = going_west,
			sources = List(src_fastned)
		),
		ChargePoint(
			name = "Fastned Lokkant",
			location = GeoPoint(51.68434, 5.89076, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("node/3211292181"),
			roadName = "A73", accessible = going_south,
			sources = List(src_fastned)
		),
		ChargePoint(
			name = "Fastned Hondsiep",
			location = GeoPoint(51.68336, 5.89377, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("node/3211288937"),
			roadName = "A73", accessible = going_north,
			sources = List(src_fastned)
		),
		ChargePoint(
			name = "Fastned Varakker",
			location = GeoPoint(51.92394, 5.60545, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("node/4461692038"),
			roadName = "A15", accessible = going_west,
			sources = List(src_fastned)
		),
		ChargePoint(
			name = "Fastned Overbroek",
			location = GeoPoint(51.92177,5.60360, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List(),
			roadName = "A15", accessible = going_east,
			sources = List(src_fastned)
		),
		ChargePoint(
			name = "Fastned Geulenkamp",
			location = GeoPoint(51.9397, 6.18151, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("node/4242454489"),
			roadName = "A18", accessible = going_west,
			sources = List(src_fastned)
		),
		ChargePoint(
			name = "Fastned de Slenk",
			location = GeoPoint(52.0077, 5.81233, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("way/387372477"),
			roadName = "A50", accessible = going_north,
			sources = List(src_fastned)
		),
		ChargePoint(
			name = "Fastned Bloemheuvel",
			location = GeoPoint(52.0498, 5.4647, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("way/387355424"),
			roadName = "A12", accessible = going_east,
			sources = List(src_fastned)
		),
		ChargePoint(
			name = "Fastned de Veenen",
			location = GeoPoint(52.06399, 5.60889, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("way/387315534"),
			roadName = "A30", accessible = going_south,
			sources = List(src_fastned)
		),
		ChargePoint(
			name = "Fastned de Poel",
			location = GeoPoint(52.06375, 5.61211, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("way/387323932"),
			roadName = "A30", accessible = going_north,
			sources = List(src_fastned)
		),
		ChargePoint(
			name = "Fastned de Kroon",
			location = GeoPoint(52.01601, 5.12062, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("way/387363916"),
			roadName = "A27", accessible = going_south,
			sources = List(src_fastned)
		),



		ChargePoint(
			name = "Fastned Patiel",
			location = GeoPoint(lat = 50.7775237, lon = 5.7296055, country = "NL"),
			operator = fastned,
			payWith = List(pay_fastned),
			osm = List("node/3211456208"),
			roadName = "A2", accessible = going_north,
			sources = List(src_fastned)
		)
	)


	//========== Campings ==========
	val campings: List[Camping] = List(
		Camping(
			"Camping le Martinet",
			location = GeoPoint(46.3716694, 5.8724914, country = "FR"),
			osm = List("node/26858816")
		),
		Camping(
			"Camping Toodlermillen",
			location = GeoPoint(49.9139, 6.00149, country = "LU"),
			electricity = "4A",
			osm = List("way/51887095"),
			phone = "00 352 83 91 89"
		),
		Camping(
			name = "Camping Piscine du Plan Incliné",
			description = "Prima camping om 1 nacht te blijven, met zwembad",
			electricity = "6-16A",
			location = GeoPoint(48.72029, 7.22742),
			phone = "00 333 87 25 30 13 / 00 336 71 21 86 91"
		),
		Camping(
			name = "Camping l'Eau Rouge",
			description = "Leuke camping dichtbij Spa, met zwembad en boogschieten",
			location = GeoPoint(50.41024, 5.95280),
			phone = "00 328 086 30 75"
		),
		Camping(
			name = "Camping Les Deux Ballons",
			description = "Camping met groot zwembad en glijbaan bij een meer; 180 plaatsen",
			electricity = "10A",
			location = GeoPoint(47.95434, 6.81026, address = "17 rue du Stade, 88560 / St. Maurice-sur-Moselle, Frankrijk"),
			phone = "00 333 29 25 17 14"
		),
		Camping(
			name = "Camping Ettelbruck",
			location = GeoPoint(49.8461, 6.08203, address = "Chemin du Camping 88, 9022 Ettelbrück Luxemburg"),
			description = "149 plaatsen; geen zwembad of zwemwater",
			electricity = "16A"
		)
	)

	val locations: List[Location] = chargePoints ++ campings

}
