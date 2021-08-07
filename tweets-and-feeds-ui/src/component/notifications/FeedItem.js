import Card from '../ui/Card';

function FeedItem(props) {
  return (
    <Card>
      <div>{props.posterName}</div>
      <div>{props.postedTime}</div>
      <div>{props.content}</div>
    </Card>
  );
}
export default FeedItem;
