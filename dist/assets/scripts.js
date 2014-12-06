(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
var Paddle, config, create, game, preload, update;

config = require("./config.coffee");

Paddle = require("./paddle.coffee");

preload = function() {
  console.log(':preload');
  return game.load.image('paddle', 'assets/images/paddle.png');
};

create = function() {
  var paddle, paddle2;
  console.log(':create');
  game.stage.backgroundColor = config.backgroundColor;
  paddle = new Paddle(game, config.colorCodes.blue);
  return paddle2 = new Paddle(game, config.colorCodes.orange);
};

update = function() {};

game = new Phaser.Game(config.screenWidth, config.screenHeight, Phaser.AUTO, '', {
  preload: preload,
  create: create,
  update: update
});


},{"./config.coffee":2,"./paddle.coffee":3}],2:[function(require,module,exports){
var config;

config = {
  backgroundColor: "#350f28",
  screenWidth: 1080,
  screenHeight: 600,
  spriteScale: 8,
  colorCodes: {
    blue: 0,
    orange: 1
  },
  paddleMargin: 40
};

module.exports = config;


},{}],3:[function(require,module,exports){
var Paddle, config;

config = require("./config.coffee");

Paddle = function(game, colorCode) {
  var x, y;
  this.colorCode = colorCode;
  x = 0;
  y = config.screenHeight / 2 - game.cache.getImage('paddle').height * config.spriteScale / 2;
  switch (colorCode) {
    case config.colorCodes.blue:
      x = config.paddleMargin;
      break;
    case config.colorCodes.orange:
      x = config.screenWidth - config.paddleMargin - game.cache.getImage('paddle').width * config.spriteScale;
  }
  this.sprite = game.add.sprite(x, y, 'paddle');
  this.sprite.scale.setTo(config.spriteScale, config.spriteScale);
  return this;
};

module.exports = Paddle;


},{"./config.coffee":2}]},{},[1])
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9ob21lL25hdGhhbmllbC9nbm9Qb3J0YWwvbm9kZV9tb2R1bGVzL2d1bHAtYnJvd3NlcmlmeS9ub2RlX21vZHVsZXMvYnJvd3NlcmlmeS9ub2RlX21vZHVsZXMvYnJvd3Nlci1wYWNrL19wcmVsdWRlLmpzIiwiL2hvbWUvbmF0aGFuaWVsL2dub1BvcnRhbC9zY3JpcHRzL2NvZmZlZS9hcHAuY29mZmVlIiwiL2hvbWUvbmF0aGFuaWVsL2dub1BvcnRhbC9zY3JpcHRzL2NvZmZlZS9jb25maWcuY29mZmVlIiwiL2hvbWUvbmF0aGFuaWVsL2dub1BvcnRhbC9zY3JpcHRzL2NvZmZlZS9wYWRkbGUuY29mZmVlIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FDQUEsSUFBQSw2Q0FBQTs7QUFBQSxNQUFBLEdBQVMsT0FBQSxDQUFRLGlCQUFSLENBQVQsQ0FBQTs7QUFBQSxNQUNBLEdBQVMsT0FBQSxDQUFRLGlCQUFSLENBRFQsQ0FBQTs7QUFBQSxPQUdBLEdBQVUsU0FBQSxHQUFBO0FBQ1IsRUFBQSxPQUFPLENBQUMsR0FBUixDQUFZLFVBQVosQ0FBQSxDQUFBO1NBRUEsSUFBSSxDQUFDLElBQUksQ0FBQyxLQUFWLENBQWdCLFFBQWhCLEVBQTBCLDBCQUExQixFQUhRO0FBQUEsQ0FIVixDQUFBOztBQUFBLE1BUUEsR0FBUyxTQUFBLEdBQUE7QUFDUCxNQUFBLGVBQUE7QUFBQSxFQUFBLE9BQU8sQ0FBQyxHQUFSLENBQVksU0FBWixDQUFBLENBQUE7QUFBQSxFQUVBLElBQUksQ0FBQyxLQUFLLENBQUMsZUFBWCxHQUE2QixNQUFNLENBQUMsZUFGcEMsQ0FBQTtBQUFBLEVBSUEsTUFBQSxHQUFhLElBQUEsTUFBQSxDQUFPLElBQVAsRUFBYSxNQUFNLENBQUMsVUFBVSxDQUFDLElBQS9CLENBSmIsQ0FBQTtTQUtBLE9BQUEsR0FBYyxJQUFBLE1BQUEsQ0FBTyxJQUFQLEVBQWEsTUFBTSxDQUFDLFVBQVUsQ0FBQyxNQUEvQixFQU5QO0FBQUEsQ0FSVCxDQUFBOztBQUFBLE1BZ0JBLEdBQVMsU0FBQSxHQUFBLENBaEJULENBQUE7O0FBQUEsSUFtQkEsR0FBVyxJQUFBLE1BQU0sQ0FBQyxJQUFQLENBQVksTUFBTSxDQUFDLFdBQW5CLEVBQWdDLE1BQU0sQ0FBQyxZQUF2QyxFQUFxRCxNQUFNLENBQUMsSUFBNUQsRUFBa0UsRUFBbEUsRUFBc0U7QUFBQSxFQUFBLE9BQUEsRUFBUyxPQUFUO0FBQUEsRUFBa0IsTUFBQSxFQUFRLE1BQTFCO0FBQUEsRUFBa0MsTUFBQSxFQUFRLE1BQTFDO0NBQXRFLENBbkJYLENBQUE7Ozs7QUNBQSxJQUFBLE1BQUE7O0FBQUEsTUFBQSxHQUFTO0FBQUEsRUFDUCxlQUFBLEVBQWlCLFNBRFY7QUFBQSxFQUdQLFdBQUEsRUFBYSxJQUhOO0FBQUEsRUFJUCxZQUFBLEVBQWMsR0FKUDtBQUFBLEVBTVAsV0FBQSxFQUFhLENBTk47QUFBQSxFQVFQLFVBQUEsRUFBWTtBQUFBLElBQ1YsSUFBQSxFQUFNLENBREk7QUFBQSxJQUVWLE1BQUEsRUFBUSxDQUZFO0dBUkw7QUFBQSxFQWFQLFlBQUEsRUFBYyxFQWJQO0NBQVQsQ0FBQTs7QUFBQSxNQWdCTSxDQUFDLE9BQVAsR0FBaUIsTUFoQmpCLENBQUE7Ozs7QUNBQSxJQUFBLGNBQUE7O0FBQUEsTUFBQSxHQUFTLE9BQUEsQ0FBUSxpQkFBUixDQUFULENBQUE7O0FBQUEsTUFFQSxHQUFTLFNBQUMsSUFBRCxFQUFPLFNBQVAsR0FBQTtBQUNQLE1BQUEsSUFBQTtBQUFBLEVBQUEsSUFBSSxDQUFDLFNBQUwsR0FBaUIsU0FBakIsQ0FBQTtBQUFBLEVBRUEsQ0FBQSxHQUFJLENBRkosQ0FBQTtBQUFBLEVBR0EsQ0FBQSxHQUFJLE1BQU0sQ0FBQyxZQUFQLEdBQXNCLENBQXRCLEdBQTBCLElBQUksQ0FBQyxLQUFLLENBQUMsUUFBWCxDQUFvQixRQUFwQixDQUE2QixDQUFDLE1BQTlCLEdBQXVDLE1BQU0sQ0FBQyxXQUE5QyxHQUE0RCxDQUgxRixDQUFBO0FBS0EsVUFBUSxTQUFSO0FBQUEsU0FDTyxNQUFNLENBQUMsVUFBVSxDQUFDLElBRHpCO0FBRUksTUFBQSxDQUFBLEdBQUksTUFBTSxDQUFDLFlBQVgsQ0FGSjtBQUNPO0FBRFAsU0FHTyxNQUFNLENBQUMsVUFBVSxDQUFDLE1BSHpCO0FBSUksTUFBQSxDQUFBLEdBQUksTUFBTSxDQUFDLFdBQVAsR0FBcUIsTUFBTSxDQUFDLFlBQTVCLEdBQTJDLElBQUksQ0FBQyxLQUFLLENBQUMsUUFBWCxDQUFvQixRQUFwQixDQUE2QixDQUFDLEtBQTlCLEdBQXNDLE1BQU0sQ0FBQyxXQUE1RixDQUpKO0FBQUEsR0FMQTtBQUFBLEVBV0EsSUFBSSxDQUFDLE1BQUwsR0FBYyxJQUFJLENBQUMsR0FBRyxDQUFDLE1BQVQsQ0FBZ0IsQ0FBaEIsRUFBbUIsQ0FBbkIsRUFBc0IsUUFBdEIsQ0FYZCxDQUFBO0FBQUEsRUFZQSxJQUFJLENBQUMsTUFBTSxDQUFDLEtBQUssQ0FBQyxLQUFsQixDQUF3QixNQUFNLENBQUMsV0FBL0IsRUFBNEMsTUFBTSxDQUFDLFdBQW5ELENBWkEsQ0FBQTtBQWNBLFNBQU8sSUFBUCxDQWZPO0FBQUEsQ0FGVCxDQUFBOztBQUFBLE1BbUJNLENBQUMsT0FBUCxHQUFpQixNQW5CakIsQ0FBQSIsImZpbGUiOiJnZW5lcmF0ZWQuanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlc0NvbnRlbnQiOlsiKGZ1bmN0aW9uIGUodCxuLHIpe2Z1bmN0aW9uIHMobyx1KXtpZighbltvXSl7aWYoIXRbb10pe3ZhciBhPXR5cGVvZiByZXF1aXJlPT1cImZ1bmN0aW9uXCImJnJlcXVpcmU7aWYoIXUmJmEpcmV0dXJuIGEobywhMCk7aWYoaSlyZXR1cm4gaShvLCEwKTt0aHJvdyBuZXcgRXJyb3IoXCJDYW5ub3QgZmluZCBtb2R1bGUgJ1wiK28rXCInXCIpfXZhciBmPW5bb109e2V4cG9ydHM6e319O3Rbb11bMF0uY2FsbChmLmV4cG9ydHMsZnVuY3Rpb24oZSl7dmFyIG49dFtvXVsxXVtlXTtyZXR1cm4gcyhuP246ZSl9LGYsZi5leHBvcnRzLGUsdCxuLHIpfXJldHVybiBuW29dLmV4cG9ydHN9dmFyIGk9dHlwZW9mIHJlcXVpcmU9PVwiZnVuY3Rpb25cIiYmcmVxdWlyZTtmb3IodmFyIG89MDtvPHIubGVuZ3RoO28rKylzKHJbb10pO3JldHVybiBzfSkiLCJjb25maWcgPSByZXF1aXJlIFwiLi9jb25maWcuY29mZmVlXCJcblBhZGRsZSA9IHJlcXVpcmUgXCIuL3BhZGRsZS5jb2ZmZWVcIlxuXG5wcmVsb2FkID0gLT5cbiAgY29uc29sZS5sb2cgJzpwcmVsb2FkJ1xuXG4gIGdhbWUubG9hZC5pbWFnZSgncGFkZGxlJywgJ2Fzc2V0cy9pbWFnZXMvcGFkZGxlLnBuZycpXG5cbmNyZWF0ZSA9IC0+XG4gIGNvbnNvbGUubG9nICc6Y3JlYXRlJ1xuXG4gIGdhbWUuc3RhZ2UuYmFja2dyb3VuZENvbG9yID0gY29uZmlnLmJhY2tncm91bmRDb2xvclxuXG4gIHBhZGRsZSA9IG5ldyBQYWRkbGUoZ2FtZSwgY29uZmlnLmNvbG9yQ29kZXMuYmx1ZSlcbiAgcGFkZGxlMiA9IG5ldyBQYWRkbGUoZ2FtZSwgY29uZmlnLmNvbG9yQ29kZXMub3JhbmdlKVxuXG51cGRhdGUgPSAtPlxuICAjIFRPRE8gdXBkYXRlXG5cbmdhbWUgPSBuZXcgUGhhc2VyLkdhbWUgY29uZmlnLnNjcmVlbldpZHRoLCBjb25maWcuc2NyZWVuSGVpZ2h0LCBQaGFzZXIuQVVUTywgJycsIHByZWxvYWQ6IHByZWxvYWQsIGNyZWF0ZTogY3JlYXRlLCB1cGRhdGU6IHVwZGF0ZVxuIiwiY29uZmlnID0ge1xuICBiYWNrZ3JvdW5kQ29sb3I6IFwiIzM1MGYyOFwiLFxuXG4gIHNjcmVlbldpZHRoOiAxMDgwLFxuICBzY3JlZW5IZWlnaHQ6IDYwMCxcblxuICBzcHJpdGVTY2FsZTogOCxcblxuICBjb2xvckNvZGVzOiB7XG4gICAgYmx1ZTogMFxuICAgIG9yYW5nZTogMVxuICB9LFxuXG4gIHBhZGRsZU1hcmdpbjogNDBcbn1cblxubW9kdWxlLmV4cG9ydHMgPSBjb25maWdcbiIsImNvbmZpZyA9IHJlcXVpcmUgXCIuL2NvbmZpZy5jb2ZmZWVcIlxuXG5QYWRkbGUgPSAoZ2FtZSwgY29sb3JDb2RlKSAtPlxuICB0aGlzLmNvbG9yQ29kZSA9IGNvbG9yQ29kZVxuXG4gIHggPSAwXG4gIHkgPSBjb25maWcuc2NyZWVuSGVpZ2h0IC8gMiAtIGdhbWUuY2FjaGUuZ2V0SW1hZ2UoJ3BhZGRsZScpLmhlaWdodCAqIGNvbmZpZy5zcHJpdGVTY2FsZSAvIDJcblxuICBzd2l0Y2ggKGNvbG9yQ29kZSlcbiAgICB3aGVuIGNvbmZpZy5jb2xvckNvZGVzLmJsdWVcbiAgICAgIHggPSBjb25maWcucGFkZGxlTWFyZ2luXG4gICAgd2hlbiBjb25maWcuY29sb3JDb2Rlcy5vcmFuZ2VcbiAgICAgIHggPSBjb25maWcuc2NyZWVuV2lkdGggLSBjb25maWcucGFkZGxlTWFyZ2luIC0gZ2FtZS5jYWNoZS5nZXRJbWFnZSgncGFkZGxlJykud2lkdGggKiBjb25maWcuc3ByaXRlU2NhbGVcblxuICB0aGlzLnNwcml0ZSA9IGdhbWUuYWRkLnNwcml0ZSh4LCB5LCAncGFkZGxlJylcbiAgdGhpcy5zcHJpdGUuc2NhbGUuc2V0VG8oY29uZmlnLnNwcml0ZVNjYWxlLCBjb25maWcuc3ByaXRlU2NhbGUpXG5cbiAgcmV0dXJuIHRoaXNcblxubW9kdWxlLmV4cG9ydHMgPSBQYWRkbGVcbiJdfQ==
