config = require "./config.coffee"
Paddle = require "./paddle.coffee"
Cuby = require "./cuby.coffee"
Wall = require "./wall.coffee"

paddles = [ ]

upKey = { }
downKey = { }

walls = { }

companion = { }

preload = ->
  console.log ':preload'

  game.load.image('paddle', 'assets/images/paddle.png')
  game.load.image('cuby', 'assets/images/qb.png')
  game.load.image('wall-vertical', 'assets/images/wall-vertical.png')
  game.load.image('wall-horizontal', 'assets/images/wall-horizontal.png')

create = ->
  console.log ':create'

  game.stage.backgroundColor = config.backgroundColor
  game.stage.smoothed = false

  game.physics.startSystem(Phaser.Physics.ARCADE)

  paddles[config.colorCodes.blue] = new Paddle(game, config.colorCodes.blue)
  paddles[config.colorCodes.orange] = new Paddle(game, config.colorCodes.orange)

  walls = game.add.group()

  buildHorizontalWalls()

  upKey = game.input.keyboard.addKey(Phaser.Keyboard.W)
  downKey = game.input.keyboard.addKey(Phaser.Keyboard.S)

  companion = new Cuby(this, new Phaser.Point(config.screenWidth/2, config.screenHeight/2))

buildHorizontalWalls = ->
  x = 0
  while x < config.screenWidth
    wall = new Wall(game, true, x, 0)
    walls.add(wall.sprite)

    wall = new Wall(game, true, x, config.screenHeight - 24)
    walls.add(wall.sprite)

    x += 100

updateBlue = ->
  console.log ':updateBlue'

  paddles[config.colorCodes.blue].update()

  # TODO handle input
  console.log('upKey' + upKey.isDown)
  if upKey.isDown
    paddles[config.colorCodes.blue].move(config.dirCodes.up)

  if downKey.isDown
    paddles[config.colorCodes.blue].move(config.dirCodes.down)

updateOrange = ->
  console.log ":updateOrange"

  paddles[config.colorCodes.blue].update

  # TODO run AI decisions

update = ->
  console.log ':update'

  updateBlue()
  updateOrange()

  game.physics.arcade.collide(companion.sprite, walls, (companionSprite, wallSprite) ->
    companion.switch(wallSprite.wall.horizontal);
  )

game = new Phaser.Game config.screenWidth, config.screenHeight, Phaser.AUTO, '', preload: preload, create: create, update: update
