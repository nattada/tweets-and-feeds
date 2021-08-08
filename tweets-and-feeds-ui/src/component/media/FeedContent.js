import { useState, useEffect } from "react";
import FeedList from "../notifications/FeedList";
import NewFeed from "../NewFeed";
import classes from "../ui/Timeline.module.css";
import feedContentClasses from "./FeedContent.module.css";

import Card from "../ui/Card";


function FeedContent() {
  const [isLoading, setIsLoading] = useState(true);
  const [loadedFeeds, setLoadedFeeds] = useState([]);

  useEffect(() => {
    setIsLoading(true);
    fetch("/feed", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        const Feeds = [];
        for (const key in data) {
          const item = {
            id: key,
            ...data[key],
          };
          Feeds.push(item);
        }
        setIsLoading(false);
        setLoadedFeeds(Feeds);
      })
      .catch(function () {
        //TODO implement ui for error
        console.log("error");
      });
  }, []);

  if (isLoading) {
    return (
      <section>
        <p>Loading...</p>
      </section>
    );
  }

  return (
    <div className={feedContentClasses.feed,feedContentClasses.card__feed}>
      <div >
      <Card>
        <div className={classes.container}>
          <ul className={classes.container__items}>
            <FeedList allFeeds={loadedFeeds} />
          </ul>
        </div>
      </Card>
      </div>
      <div>
        <NewFeed />
      </div>
    </div>
  );
}

export default FeedContent;
