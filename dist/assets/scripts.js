(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
var config, create, game, preload, update;

config = require("./config.coffee");

preload = function() {
  console.log(':preload');
  return game.load.image('paddle', 'assets/images/paddle.png');
};

create = function() {
  var paddle;
  console.log(':create');
  game.stage.backgroundColor = config.backgroundColor;
  paddle = game.add.sprite(40, 300, 'paddle');
  return paddle.scale.setTo(8, 8);
};

update = function() {};

game = new Phaser.Game(1080, 600, Phaser.AUTO, '', {
  preload: preload,
  create: create,
  update: update
});


},{"./config.coffee":2}],2:[function(require,module,exports){
var config;

config = {
  backgroundColor: "#350f28",
  spriteScale: 8,
  colorCodes: {
    blue: 0,
    orange: 1
  },
  paddleMargin: 40
};

module.exports = config;


},{}]},{},[1])
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9ob21lL25hdGhhbmllbC9nbm9Qb3J0YWwvbm9kZV9tb2R1bGVzL2d1bHAtYnJvd3NlcmlmeS9ub2RlX21vZHVsZXMvYnJvd3NlcmlmeS9ub2RlX21vZHVsZXMvYnJvd3Nlci1wYWNrL19wcmVsdWRlLmpzIiwiL2hvbWUvbmF0aGFuaWVsL2dub1BvcnRhbC9zY3JpcHRzL2NvZmZlZS9hcHAuY29mZmVlIiwiL2hvbWUvbmF0aGFuaWVsL2dub1BvcnRhbC9zY3JpcHRzL2NvZmZlZS9jb25maWcuY29mZmVlIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FDQUEsSUFBQSxxQ0FBQTs7QUFBQSxNQUFBLEdBQVMsT0FBQSxDQUFRLGlCQUFSLENBQVQsQ0FBQTs7QUFBQSxPQUVBLEdBQVUsU0FBQSxHQUFBO0FBQ1IsRUFBQSxPQUFPLENBQUMsR0FBUixDQUFZLFVBQVosQ0FBQSxDQUFBO1NBRUEsSUFBSSxDQUFDLElBQUksQ0FBQyxLQUFWLENBQWdCLFFBQWhCLEVBQTBCLDBCQUExQixFQUhRO0FBQUEsQ0FGVixDQUFBOztBQUFBLE1BT0EsR0FBUyxTQUFBLEdBQUE7QUFDUCxNQUFBLE1BQUE7QUFBQSxFQUFBLE9BQU8sQ0FBQyxHQUFSLENBQVksU0FBWixDQUFBLENBQUE7QUFBQSxFQUVBLElBQUksQ0FBQyxLQUFLLENBQUMsZUFBWCxHQUE2QixNQUFNLENBQUMsZUFGcEMsQ0FBQTtBQUFBLEVBSUEsTUFBQSxHQUFTLElBQUksQ0FBQyxHQUFHLENBQUMsTUFBVCxDQUFnQixFQUFoQixFQUFvQixHQUFwQixFQUF5QixRQUF6QixDQUpULENBQUE7U0FLQSxNQUFNLENBQUMsS0FBSyxDQUFDLEtBQWIsQ0FBbUIsQ0FBbkIsRUFBc0IsQ0FBdEIsRUFOTztBQUFBLENBUFQsQ0FBQTs7QUFBQSxNQWVBLEdBQVMsU0FBQSxHQUFBLENBZlQsQ0FBQTs7QUFBQSxJQWtCQSxHQUFXLElBQUEsTUFBTSxDQUFDLElBQVAsQ0FBWSxJQUFaLEVBQWtCLEdBQWxCLEVBQXVCLE1BQU0sQ0FBQyxJQUE5QixFQUFvQyxFQUFwQyxFQUF3QztBQUFBLEVBQUEsT0FBQSxFQUFTLE9BQVQ7QUFBQSxFQUFrQixNQUFBLEVBQVEsTUFBMUI7QUFBQSxFQUFrQyxNQUFBLEVBQVEsTUFBMUM7Q0FBeEMsQ0FsQlgsQ0FBQTs7OztBQ0FBLElBQUEsTUFBQTs7QUFBQSxNQUFBLEdBQVM7QUFBQSxFQUNQLGVBQUEsRUFBaUIsU0FEVjtBQUFBLEVBRVAsV0FBQSxFQUFhLENBRk47QUFBQSxFQUlQLFVBQUEsRUFBWTtBQUFBLElBQ1YsSUFBQSxFQUFNLENBREk7QUFBQSxJQUVWLE1BQUEsRUFBUSxDQUZFO0dBSkw7QUFBQSxFQVNQLFlBQUEsRUFBYyxFQVRQO0NBQVQsQ0FBQTs7QUFBQSxNQVlNLENBQUMsT0FBUCxHQUFpQixNQVpqQixDQUFBIiwiZmlsZSI6ImdlbmVyYXRlZC5qcyIsInNvdXJjZVJvb3QiOiIiLCJzb3VyY2VzQ29udGVudCI6WyIoZnVuY3Rpb24gZSh0LG4scil7ZnVuY3Rpb24gcyhvLHUpe2lmKCFuW29dKXtpZighdFtvXSl7dmFyIGE9dHlwZW9mIHJlcXVpcmU9PVwiZnVuY3Rpb25cIiYmcmVxdWlyZTtpZighdSYmYSlyZXR1cm4gYShvLCEwKTtpZihpKXJldHVybiBpKG8sITApO3Rocm93IG5ldyBFcnJvcihcIkNhbm5vdCBmaW5kIG1vZHVsZSAnXCIrbytcIidcIil9dmFyIGY9bltvXT17ZXhwb3J0czp7fX07dFtvXVswXS5jYWxsKGYuZXhwb3J0cyxmdW5jdGlvbihlKXt2YXIgbj10W29dWzFdW2VdO3JldHVybiBzKG4/bjplKX0sZixmLmV4cG9ydHMsZSx0LG4scil9cmV0dXJuIG5bb10uZXhwb3J0c312YXIgaT10eXBlb2YgcmVxdWlyZT09XCJmdW5jdGlvblwiJiZyZXF1aXJlO2Zvcih2YXIgbz0wO288ci5sZW5ndGg7bysrKXMocltvXSk7cmV0dXJuIHN9KSIsImNvbmZpZyA9IHJlcXVpcmUgXCIuL2NvbmZpZy5jb2ZmZWVcIlxuXG5wcmVsb2FkID0gLT5cbiAgY29uc29sZS5sb2cgJzpwcmVsb2FkJ1xuXG4gIGdhbWUubG9hZC5pbWFnZSgncGFkZGxlJywgJ2Fzc2V0cy9pbWFnZXMvcGFkZGxlLnBuZycpXG5cbmNyZWF0ZSA9IC0+XG4gIGNvbnNvbGUubG9nICc6Y3JlYXRlJ1xuXG4gIGdhbWUuc3RhZ2UuYmFja2dyb3VuZENvbG9yID0gY29uZmlnLmJhY2tncm91bmRDb2xvclxuXG4gIHBhZGRsZSA9IGdhbWUuYWRkLnNwcml0ZSg0MCwgMzAwLCAncGFkZGxlJylcbiAgcGFkZGxlLnNjYWxlLnNldFRvKDgsIDgpXG5cbnVwZGF0ZSA9IC0+XG4gICMgVE9ETyB1cGRhdGVcblxuZ2FtZSA9IG5ldyBQaGFzZXIuR2FtZSAxMDgwLCA2MDAsIFBoYXNlci5BVVRPLCAnJywgcHJlbG9hZDogcHJlbG9hZCwgY3JlYXRlOiBjcmVhdGUsIHVwZGF0ZTogdXBkYXRlXG4iLCJjb25maWcgPSB7XG4gIGJhY2tncm91bmRDb2xvcjogXCIjMzUwZjI4XCIsXG4gIHNwcml0ZVNjYWxlOiA4LFxuXG4gIGNvbG9yQ29kZXM6IHtcbiAgICBibHVlOiAwXG4gICAgb3JhbmdlOiAxXG4gIH0sXG5cbiAgcGFkZGxlTWFyZ2luOiA0MFxufVxuXG5tb2R1bGUuZXhwb3J0cyA9IGNvbmZpZ1xuIl19
