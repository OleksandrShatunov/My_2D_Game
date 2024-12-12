package main;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.soidArea.x;
		int entityRightWorldX = entity.worldX + entity.soidArea.x + entity.soidArea.width;
		int entityTopWorldY = entity.worldY + entity.soidArea.y;
		int entityBottomWorldY = entity.worldY + entity.soidArea.y + entity.soidArea.height;

		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;

		int tileNum1, tileNum2;

		switch (entity.direction) {
			case "up":
				entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol] [entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol] [entityTopRow];
				if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case "down":
				entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol] [entityBottomRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol] [entityBottomRow];
				if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case "left":
				entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol] [entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityLeftCol] [entityBottomRow];
				if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case "right":
				entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityRightCol] [entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol] [entityBottomRow];
				if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				break;
		}
	}
	public int checkObject(Entity entity, boolean player) {
		int index = 999;

		for (int i = 0; i < gp.obj.length; i++) {
			if (gp.obj[i] != null) {
				entity.soidArea.x = entity.worldX + entity.soidArea.x;
				entity.soidArea.y = entity.worldY + entity.soidArea.y;
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

				switch (entity.direction) {
					case "up":
						entity.soidArea.y -= entity.speed;
						if (entity.soidArea.intersects(gp.obj[i].solidArea)) {
							if (gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							if (player == true) {
								index = i;
							}
						}
						break;
					case "down":
						entity.soidArea.y += entity.speed;
						if (entity.soidArea.intersects(gp.obj[i].solidArea)) {
							if (gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							if (player == true) {
								index = i;
							}
						}
						break;
					case "left":
						entity.soidArea.x -= entity.speed;
						if (entity.soidArea.intersects(gp.obj[i].solidArea)) {
							if (gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							if (player == true) {
								index = i;
							}
						}
						break;
					case "right":
						entity.soidArea.x += entity.speed;
						if (entity.soidArea.intersects(gp.obj[i].solidArea)) {
							if (gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							if (player == true) {
								index = i;
							}
						}
						break;
				}
				entity.soidArea.x = entity.solidAreaDefaultX;
				entity.soidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}

		return index;
	}
}