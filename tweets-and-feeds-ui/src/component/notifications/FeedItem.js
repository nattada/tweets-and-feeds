import Card from '../ui/Card';

function FeedItem(props) {
  return (
    <Card>
      <div>{props.postedBy}</div>
      <div>{props.postedOn}</div>
      <div>{props.content}</div>
    </Card>
  );
}
export default FeedItem;
