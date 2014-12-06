config = require "./config.coffee"
Paddle = require "./paddle.coffee"
Cuby = require "./cuby.coffee"
Wall = require "./wall.coffee"

paddles = [ ]

upKey = { }
downKey = { }

upKey2 = { }
downKey2 = { }

walls = { }

companion = { }

paddlesGroup = { }

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
  paddlesGroup = game.add.group()
  paddlesGroup.add(paddles[config.colorCodes.blue].sprite)
  paddlesGroup.add(paddles[config.colorCodes.orange].sprite)

  walls = game.add.group()

  buildHorizontalWalls()

  upKey = game.input.keyboard.addKey(Phaser.Keyboard.W)
  downKey = game.input.keyboard.addKey(Phaser.Keyboard.S)

  upKey.onDown.add(->
    paddles[config.colorCodes.blue].move(config.dirCodes.up)
  )

  upKey.onUp.add(->
    paddles[config.colorCodes.blue].sprite.body.velocity = new Phaser.Point(0, 0)
  )

  downKey.onDown.add(->
    paddles[config.colorCodes.blue].move(config.dirCodes.down)
  )

  downKey.onUp.add(->
    paddles[config.colorCodes.blue].sprite.body.velocity = new Phaser.Point(0, 0)
  )

  # TODO remove this
  upKey2 = game.input.keyboard.addKey(Phaser.Keyboard.UP)
  downKey2 = game.input.keyboard.addKey(Phaser.Keyboard.DOWN)

  companion = new Cuby(this, new Phaser.Point(config.screenWidth/2, config.screenHeight/2))
  setTimeout ->
    companion.fire(new Phaser.Point(109,100))
  , 500

  companion.addEvent(->
    console.log('tee hee hee')
  )


buildHorizontalWalls = ->
  x = 0
  while x < config.screenWidth
    wall = new Wall(game, true, x, 0)
    walls.add(wall.sprite)

    wall = new Wall(game, true, x, config.screenHeight - 24)
    walls.add(wall.sprite)

    x += 100


updateOrange = ->
  # TODO run AI decisions

update = ->
  updateOrange()

  game.physics.arcade.collide(companion.sprite, walls, ->
    companion.onCollide()
  )
  game.physics.arcade.collide(companion.sprite, paddlesGroup, ->
    companion.onCollide()
  )

  # bound paddles to the walls
  paddlesGroup.forEach((sprite) ->
    if sprite.y < 24
      sprite.y = 24
    if sprite.y > 600 - 184
      sprite.y = 600 - 184
  )


game = new Phaser.Game config.screenWidth, config.screenHeight, Phaser.AUTO, '', preload: preload, create: create, update: update
