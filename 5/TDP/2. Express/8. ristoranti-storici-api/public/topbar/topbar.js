const topbarElement = document.getElementById("topbar");

const PAGES = {
	"Home": "/",
	"Aggiungi ristorante": "/aggiungi_ristorante.html",
}

async function injectTopbar() {
	topbarElement.innerHTML = `
		<style>
			#topbar ul {
				list-style-type: none;
				margin: 0;
				padding: 0;
				overflow: hidden;
				background-color: #333;
			}

			#topbar li {
				float: left;
			}

			#topbar li a {
				display: block;
				color: white;
				text-align: center;
				padding: 14px 16px;
				text-decoration: none;
			}

			#topbar li a:hover:not(.active) {
				background-color: #111;
			}

			#topbar .active {
				background-color: #04AA6D;
			}
		</style>

		<ul>
			<li><a>Ristoranti storici</a></li>

			${Object.keys(PAGES).map(page => `
				<li><a href="${PAGES[page]}" class="${window.location.pathname === PAGES[page] ? "active" : ""}">${page}</a></li>
			`).join("")}
		</ul>
	`;
}

injectTopbar();
