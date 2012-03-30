package test.path {
	import gear.utils.MathUtil;
	import gear.core.Game;
	import gear.game.hit.BarrierData;
	import gear.game.path.AStar;
	import gear.game.path.IMapHit;
	import gear.game.path.PathUtil;
	import gear.utils.GDrawUtil;

	import flash.geom.Point;
	import flash.geom.Rectangle;

	/**
	 * @author flashpf
	 */
	public class TestPath extends Game implements IMapHit {
		private var _astar : AStar;
		private var _hits : Array;

		override protected function startup() : void {
			_astar = new AStar(this);
			var start : Point = new Point(80, 80);
			var end : Point = new Point(225, 110);
			GDrawUtil.drawLine(graphics, start.x, start.y, end.x, end.y);
			var barrier : BarrierData = new BarrierData(20, 10);
			var node : Point = PathUtil.toNode(barrier.halfW, barrier.halfH, start.x, start.y, end.x, end.y);
			var path : Array = PathUtil.lineToPath(start.x, start.y, end.x, end.y, barrier.halfW);
			var point : Point;
			for each (point in path) {
				drawCircle(point.x, point.y, barrier.halfW);
			}
			point = PathUtil.toXY(barrier.halfW, barrier.halfH, start.x, start.y, node.x, node.y);

			// drawRect(point.x, point.y, barrier.halfW, barrier.halfH);
			_hits = new Array();
			_hits.push(new Rectangle(130, 100, 50, 50));
			for each (var rect:Rectangle in _hits) {
				drawRect(rect.x, rect.y, rect.width * 0.5, rect.height * 0.5);
			}
			var paths : Array = _astar.find(20, 10, start.x, start.y, end.x, end.y);
			drawPath(paths);
			trace("##",MathUtil.getMinStep(15, 6));
		}

		public function drawCircle(x : int, y : int, radius : int) : void {
			graphics.lineStyle(1, 0xFF0000);
			graphics.drawCircle(x, y, radius);
		}

		public function drawRect(x : int, y : int, halfW : int, halfH : int) : void {
			graphics.lineStyle(1, 0x0000FF);
			graphics.drawRect(x - halfW, y - halfH, halfW * 2, halfH * 2);
		}

		public function drawPath(path : Array) : void {
			trace(path);
		}

		public function canPass(halfW : int, halfH : int, startX : int, startY : int, endX : int, endY : int) : Boolean {
			var source : Rectangle = new Rectangle(endX - halfW, endY - halfH, halfW * 2, halfH * 2);
			var hit : Boolean = false;
			for each (var target:Rectangle in _hits) {
				hit = source.intersects(target);
				if (hit) {
					break;
				}
			}
			return true;
		}
	}
}
