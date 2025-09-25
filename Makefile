COMPOSE_FILE=docker-compose.yml


build:
	docker compose -f $(COMPOSE_FILE) build


up:
	docker compose -f $(COMPOSE_FILE) up -d


down:
	docker compose -f $(COMPOSE_FILE) down


restart: down build up


logs:
	docker compose -f $(COMPOSE_FILE) logs -f


clean:
	docker compose -f $(COMPOSE_FILE) down -v --rmi all
