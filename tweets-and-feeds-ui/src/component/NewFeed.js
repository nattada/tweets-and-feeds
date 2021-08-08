import NewFeedForm  from "./NewFeedForm";

function NewFeed() {

  function addFeedHandler(feedData) {
    fetch("/feed", {
      method: "POST",
      body: JSON.stringify(feedData),
      headers: {
        "Content-Type": "application/json",
      }
    });
  }

  return (
    <div>
      <NewFeedForm onAddFeed={addFeedHandler} />
    </div>
  );
}
export default NewFeed;
